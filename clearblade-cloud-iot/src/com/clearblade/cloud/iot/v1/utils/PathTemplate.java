package com.clearblade.cloud.iot.v1.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.*;

import javax.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathTemplate {
    public static final String HOSTNAME_VAR = "$hostname";
    private static final Pattern CUSTOM_VERB_PATTERN = Pattern.compile(":([^/*}{=]+)$");
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^(\\w+:)?//");
    private static final Splitter SLASH_SPLITTER = Splitter.on('/').trimResults();
    private static final Pattern COMPLEX_DELIMITER_PATTERN = Pattern.compile("[_\\-\\.~]");
    private static final Pattern MULTIPLE_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}[_\\-\\.~]{2,}\\{");
    private static final Pattern MISSING_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}\\{");
    private static final Pattern INVALID_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}[^_\\-\\.~]\\{");
    private static final Pattern END_SEGMENT_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}[_\\-\\.~]{1}");
    private final ImmutableList<Segment> segments;
    private final ImmutableMap<String, Segment> bindings;
    private final boolean urlEncoding;

    public static PathTemplate create(String template) {
        return create(template, true);
    }

    public static PathTemplate createWithoutUrlEncoding(String template) {
        return create(template, false);
    }

    private static PathTemplate create(String template, boolean urlEncoding) {
        return new PathTemplate(parseTemplate(template), urlEncoding);
    }

    private PathTemplate(Iterable<Segment> segments, boolean urlEncoding) {
        this.segments = ImmutableList.copyOf(segments);
        if (this.segments.isEmpty()) {
            throw new ValidationException("template cannot be empty.", new Object[0]);
        } else {
            Map<String, Segment> bindings = Maps.newLinkedHashMap();
            UnmodifiableIterator var4 = this.segments.iterator();

            while(var4.hasNext()) {
                Segment seg = (Segment)var4.next();
                if (seg.kind() == PathTemplate.SegmentKind.BINDING) {
                    if (bindings.containsKey(seg.value())) {
                        throw new ValidationException("Duplicate binding '%s'", new Object[]{seg.value()});
                    }

                    bindings.put(seg.value(), seg);
                }
            }

            this.bindings = ImmutableMap.copyOf(bindings);
            this.urlEncoding = urlEncoding;
        }
    }

    public Set<String> vars() {
        return this.bindings.keySet();
    }

    public PathTemplate parentTemplate() {
        int i = this.segments.size();
        --i;
        Segment seg = (Segment)this.segments.get(i);
        if (seg.kind() == PathTemplate.SegmentKind.END_BINDING) {
            while(i > 0) {
                --i;
                if (((Segment)this.segments.get(i)).kind() == PathTemplate.SegmentKind.BINDING) {
                    break;
                }
            }
        }

        if (i == 0) {
            throw new ValidationException("template does not have a parent", new Object[0]);
        } else {
            return new PathTemplate(this.segments.subList(0, i), this.urlEncoding);
        }
    }

    public PathTemplate withoutVars() {
        StringBuilder result = new StringBuilder();
        ListIterator<Segment> iterator = this.segments.listIterator();
        boolean start = true;

        while(iterator.hasNext()) {
            Segment seg = (Segment)iterator.next();
            switch (seg.kind()) {
                case END_BINDING:
                case BINDING:
                    break;
                default:
                    if (!start) {
                        result.append(seg.separator());
                    } else {
                        start = false;
                    }

                    result.append(seg.value());
            }
        }

        return create(result.toString(), this.urlEncoding);
    }

    public PathTemplate subTemplate(String varName) {
        List<Segment> sub = Lists.newArrayList();
        boolean inBinding = false;
        UnmodifiableIterator var4 = this.segments.iterator();

        while(true) {
            while(var4.hasNext()) {
                Segment seg = (Segment)var4.next();
                if (seg.kind() == PathTemplate.SegmentKind.BINDING && seg.value().equals(varName)) {
                    inBinding = true;
                } else if (inBinding) {
                    if (seg.kind() == PathTemplate.SegmentKind.END_BINDING) {
                        return create(toSyntax(sub, true), this.urlEncoding);
                    }

                    sub.add(seg);
                }
            }

            throw new ValidationException(String.format("Variable '%s' is undefined in template '%s'", varName, this.toRawString()), new Object[0]);
        }
    }

    public boolean endsWithLiteral() {
        return ((Segment)this.segments.get(this.segments.size() - 1)).kind() == PathTemplate.SegmentKind.LITERAL;
    }

    public boolean endsWithCustomVerb() {
        return ((Segment)this.segments.get(this.segments.size() - 1)).kind() == PathTemplate.SegmentKind.CUSTOM_VERB;
    }

    public TemplatedResourceName parse(String path) {
        return TemplatedResourceName.create(this, path);
    }

    @Nullable
    public String singleVar() {
        return this.bindings.size() == 1 ? (String)((Map.Entry)this.bindings.entrySet().iterator().next()).getKey() : null;
    }

    public void validate(String path, String exceptionMessagePrefix) {
        if (!this.matches(path)) {
            throw new ValidationException(String.format("%s: Parameter \"%s\" must be in the form \"%s\"", exceptionMessagePrefix, path, this.toString()), new Object[0]);
        }
    }

    public Map<String, String> validatedMatch(String path, String exceptionMessagePrefix) {
        Map<String, String> matchMap = this.match(path);
        if (matchMap == null) {
            throw new ValidationException(String.format("%s: Parameter \"%s\" must be in the form \"%s\"", exceptionMessagePrefix, path, this.toString()), new Object[0]);
        } else {
            return matchMap;
        }
    }

    public boolean matches(String path) {
        return this.match(path) != null;
    }

    @Nullable
    public Map<String, String> match(String path) {
        return this.match(path, false);
    }

    @Nullable
    public Map<String, String> matchFromFullName(String path) {
        return this.match(path, true);
    }

    private Map<String, String> match(String path, boolean forceHostName) {
        Segment last = (Segment)this.segments.get(this.segments.size() - 1);
        Matcher matcher;
        if (last.kind() == PathTemplate.SegmentKind.CUSTOM_VERB) {
            matcher = CUSTOM_VERB_PATTERN.matcher(path);
            if (!matcher.find() || !this.decodeUrl(matcher.group(1)).equals(last.value())) {
                return null;
            }

            path = path.substring(0, matcher.start(0));
        }

        matcher = HOSTNAME_PATTERN.matcher(path);
        boolean withHostName = matcher.find();
        if (withHostName) {
            path = matcher.replaceFirst("");
        }

        List<String> input = SLASH_SPLITTER.splitToList(path);
        int inPos = 0;
        Map<String, String> values = Maps.newLinkedHashMap();
        if (withHostName || forceHostName) {
            if (input.isEmpty()) {
                return null;
            }

            String hostName = (String)input.get(inPos++);
            if (withHostName) {
                hostName = matcher.group(0) + hostName;
            }

            values.put("$hostname", hostName);
        }

        if (withHostName) {
            inPos = this.alignInputToAlignableSegment(input, inPos, (Segment)this.segments.get(0));
        }

        if (!this.match(input, inPos, this.segments, 0, values)) {
            return null;
        } else {
            return ImmutableMap.copyOf(values);
        }
    }

    private int alignInputToAlignableSegment(List<String> input, int inPos, Segment segment) {
        switch (segment.kind()) {
            case BINDING:
                inPos = this.alignInputPositionToLiteral(input, inPos, segment.value() + "s");
                return inPos + 1;
            case LITERAL:
                return this.alignInputPositionToLiteral(input, inPos, segment.value());
            default:
                return inPos;
        }
    }

    private int alignInputPositionToLiteral(List<String> input, int inPos, String literalSegmentValue) {
        while(inPos < input.size()) {
            if (literalSegmentValue.equals(input.get(inPos))) {
                return inPos;
            }

            ++inPos;
        }

        return inPos;
    }

    private boolean match(List<String> input, int inPos, List<Segment> segments, int segPos, Map<String, String> values) {
        String currentVar = null;
        List<String> modifiableInput = new ArrayList(input);

        while(true) {
            while(segPos < segments.size()) {
                Segment seg = (Segment)segments.get(segPos++);
                int complexSeparatorIndex;
                switch (seg.kind()) {
                    case CUSTOM_VERB:
                    default:
                        break;
                    case END_BINDING:
                        currentVar = null;
                        break;
                    case BINDING:
                        currentVar = seg.value();
                        break;
                    case LITERAL:
                    case WILDCARD:
                        if (inPos >= modifiableInput.size()) {
                            return false;
                        }

                        String next = this.decodeUrl((String)modifiableInput.get(inPos++));
                        if (seg.kind() == PathTemplate.SegmentKind.LITERAL && !seg.value().equals(next)) {
                            return false;
                        }

                        if (seg.kind() == PathTemplate.SegmentKind.WILDCARD && !seg.complexSeparator().isEmpty()) {
                            complexSeparatorIndex = next.indexOf(seg.complexSeparator());
                            if (complexSeparatorIndex < 0) {
                                return false;
                            }

                            modifiableInput.add(inPos, next.substring(complexSeparatorIndex + 1));
                            next = next.substring(0, complexSeparatorIndex);
                            modifiableInput.set(inPos - 1, next);
                        }

                        if (currentVar != null) {
                            values.put(currentVar, concatCaptures((String)values.get(currentVar), next));
                        }
                        break;
                    case PATH_WILDCARD:
                        complexSeparatorIndex = 0;
                        int available = segPos;

                        while(available < segments.size()) {
                            switch (((Segment)segments.get(available)).kind()) {
                                default:
                                    ++complexSeparatorIndex;
                                case CUSTOM_VERB:
                                case END_BINDING:
                                case BINDING:
                                    ++available;
                            }
                        }

                        available = modifiableInput.size() - inPos - complexSeparatorIndex;
                        if (available == 0 && !values.containsKey(currentVar)) {
                            values.put(currentVar, "");
                        }

                        while(available-- > 0) {
                            values.put(currentVar, concatCaptures((String)values.get(currentVar), this.decodeUrl((String)modifiableInput.get(inPos++))));
                        }
                }
            }

            return inPos == modifiableInput.size();
        }
    }

    private static String concatCaptures(@Nullable String cur, String next) {
        return cur == null ? next : cur + "/" + next;
    }

    public String instantiate(Map<String, String> values) {
        return this.instantiate(values, false);
    }

    public String instantiate(String... keysAndValues) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();

        for(int i = 0; i < keysAndValues.length; i += 2) {
            builder.put(keysAndValues[i], keysAndValues[i + 1]);
        }

        return this.instantiate((Map)builder.build());
    }

    public String instantiatePartial(Map<String, String> values) {
        return this.instantiate(values, true);
    }

    private String instantiate(Map<String, String> values, boolean allowPartial) {
        StringBuilder result = new StringBuilder();
        if (values.containsKey("$hostname")) {
            result.append((String)values.get("$hostname"));
            result.append('/');
        }

        boolean continueLast = true;
        boolean skip = false;
        ListIterator<Segment> iterator = this.segments.listIterator();
        String prevSeparator = "";

        while(true) {
            while(true) {
                while(iterator.hasNext()) {
                    Segment seg = (Segment)iterator.next();
                    String var;
                    if (!skip && !continueLast) {
                        var = !prevSeparator.isEmpty() && iterator.hasNext() ? prevSeparator : seg.separator();
                        result.append(var);
                        prevSeparator = seg.complexSeparator().isEmpty() ? seg.separator() : seg.complexSeparator();
                    }

                    continueLast = false;
                    switch (seg.kind()) {
                        case END_BINDING:
                            if (!skip) {
                                result.append('}');
                            }

                            skip = false;
                            break;
                        case BINDING:
                            var = seg.value();
                            String value = (String)values.get(seg.value());
                            if (value == null) {
                                if (!allowPartial) {
                                    throw new ValidationException(String.format("Unbound variable '%s'. Bindings: %s", var, values), new Object[0]);
                                }

                                if (var.startsWith("$")) {
                                    result.append(((Segment)iterator.next()).value());
                                    iterator.next();
                                } else {
                                    result.append('{');
                                    result.append(seg.value());
                                    result.append('=');
                                    continueLast = true;
                                }
                                break;
                            }

                            Segment next = (Segment)iterator.next();
                            Segment nextNext = (Segment)iterator.next();
                            boolean pathEscape = next.kind() == PathTemplate.SegmentKind.PATH_WILDCARD || nextNext.kind() != PathTemplate.SegmentKind.END_BINDING;
                            restore(iterator, iterator.nextIndex() - 2);
                            if (!pathEscape) {
                                result.append(this.encodeUrl(value));
                            } else {
                                boolean first = true;
                                Iterator var15 = SLASH_SPLITTER.split(value).iterator();

                                while(var15.hasNext()) {
                                    String subSeg = (String)var15.next();
                                    if (!first) {
                                        result.append('/');
                                    }

                                    first = false;
                                    result.append(this.encodeUrl(subSeg));
                                }
                            }

                            skip = true;
                            break;
                        default:
                            if (!skip) {
                                result.append(seg.value());
                            }
                    }
                }

                return result.toString();
            }
        }
    }

    public String encode(String... values) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        int i = 0;
        String[] var4 = values;
        int var5 = values.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String value = var4[var6];
            builder.put("$" + i++, value);
        }

        return this.instantiate((Map)builder.build());
    }

    public List<String> decode(String path) {
        Map<String, String> match = this.match(path);
        if (match == null) {
            throw new IllegalArgumentException(String.format("template '%s' does not match '%s'", this, path));
        } else {
            List<String> result = Lists.newArrayList();
            Iterator var4 = match.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var4.next();
                String key = (String)entry.getKey();
                if (!key.startsWith("$")) {
                    throw new IllegalArgumentException("template must not contain named bindings");
                }

                int i = Integer.parseInt(key.substring(1));

                while(result.size() <= i) {
                    result.add("");
                }

                result.set(i, entry.getValue());
            }

            return ImmutableList.copyOf(result);
        }
    }

    private static ImmutableList<Segment> parseTemplate(String template) {
        if (template.startsWith("/")) {
            template = template.substring(1);
        }

        Matcher matcher = CUSTOM_VERB_PATTERN.matcher(template);
        String customVerb = null;
        if (matcher.find()) {
            customVerb = matcher.group(1);
            template = template.substring(0, matcher.start(0));
        }

        ImmutableList.Builder<Segment> builder = ImmutableList.builder();
        String varName = null;
        int freeWildcardCounter = 0;
        int pathWildCardBound = 0;
        Iterator var7 = Splitter.on('/').trimResults().split(template).iterator();

        while(true) {
            while(var7.hasNext()) {
                String seg = (String)var7.next();
                if (!seg.equals("_deleted-topic_")) {
                    boolean isLastSegment = template.indexOf(seg) + seg.length() == template.length();
                    boolean isCollectionWildcard = !isLastSegment && (seg.equals("-") || seg.equals("-}"));
                    if (!isCollectionWildcard && isSegmentBeginOrEndInvalid(seg)) {
                        throw new ValidationException("parse error: invalid begin or end character in '%s'", new Object[]{seg});
                    }

                    if (MULTIPLE_COMPLEX_DELIMITER_PATTERN.matcher(seg).find() || MISSING_COMPLEX_DELIMITER_PATTERN.matcher(seg).find()) {
                        throw new ValidationException("parse error: missing or 2+ consecutive delimiter characters in '%s'", new Object[]{seg});
                    }

                    boolean bindingStarts = seg.startsWith("{");
                    boolean implicitWildcard = false;
                    boolean complexDelimiterFound = false;
                    if (bindingStarts) {
                        if (varName != null) {
                            throw new ValidationException("parse error: nested binding in '%s'", new Object[]{template});
                        }

                        seg = seg.substring(1);
                        if (INVALID_COMPLEX_DELIMITER_PATTERN.matcher(seg).find()) {
                            throw new ValidationException("parse error: invalid complex resource ID delimiter character in '%s'", new Object[]{seg});
                        }

                        Matcher complexPatternDelimiterMatcher = END_SEGMENT_COMPLEX_DELIMITER_PATTERN.matcher(seg);
                        complexDelimiterFound = !isCollectionWildcard && complexPatternDelimiterMatcher.find();
                        if (complexDelimiterFound) {
                            builder.addAll(parseComplexResourceId(seg));
                        } else {
                            int i = seg.indexOf(61);
                            if (i <= 0) {
                                if (!seg.endsWith("}")) {
                                    throw new ValidationException("parse error: invalid binding syntax in '%s'", new Object[]{template});
                                }

                                implicitWildcard = true;
                                varName = seg.substring(0, seg.length() - 1).trim();
                                seg = seg.substring(seg.length() - 1).trim();
                            } else if (seg.indexOf(45) <= 0 && isCollectionWildcard) {
                                implicitWildcard = true;
                            } else {
                                varName = seg.substring(0, i).trim();
                                seg = seg.substring(i + 1).trim();
                            }

                            builder.add(PathTemplate.Segment.create(PathTemplate.SegmentKind.BINDING, varName));
                        }
                    }

                    if (!complexDelimiterFound) {
                        boolean bindingEnds = seg.endsWith("}");
                        if (bindingEnds) {
                            seg = seg.substring(0, seg.length() - 1).trim();
                        }

                        switch (seg) {
                            case "**":
                            case "*":
                                if ("**".equals(seg)) {
                                    ++pathWildCardBound;
                                }

                                Segment wildcard = seg.length() == 2 ? PathTemplate.Segment.PATH_WILDCARD : PathTemplate.Segment.WILDCARD;
                                if (varName == null) {
                                    builder.add(PathTemplate.Segment.create(PathTemplate.SegmentKind.BINDING, "$" + freeWildcardCounter));
                                    ++freeWildcardCounter;
                                    builder.add(wildcard);
                                    builder.add(PathTemplate.Segment.END_BINDING);
                                } else {
                                    builder.add(wildcard);
                                }
                                break;
                            case "":
                                if (!bindingEnds) {
                                    throw new ValidationException("parse error: empty segment not allowed in '%s'", new Object[]{template});
                                }
                                break;
                            case "-":
                                builder.add(PathTemplate.Segment.WILDCARD);
                                implicitWildcard = false;
                                break;
                            default:
                                builder.add(PathTemplate.Segment.create(PathTemplate.SegmentKind.LITERAL, seg));
                        }

                        if (bindingEnds && !complexDelimiterFound) {
                            varName = null;
                            if (implicitWildcard) {
                                builder.add(PathTemplate.Segment.WILDCARD);
                            }

                            builder.add(PathTemplate.Segment.END_BINDING);
                        }

                        if (pathWildCardBound > 1) {
                            throw new ValidationException("parse error: pattern must not contain more than one path wildcard ('**') in '%s'", new Object[]{template});
                        }
                    }
                } else {
                    builder.add(PathTemplate.Segment.create(PathTemplate.SegmentKind.LITERAL, seg));
                }
            }

            if (customVerb != null) {
                builder.add(PathTemplate.Segment.create(PathTemplate.SegmentKind.CUSTOM_VERB, customVerb));
            }

            return builder.build();
        }
    }

    private static boolean isSegmentBeginOrEndInvalid(String seg) {
        if (seg.length() == 1 && COMPLEX_DELIMITER_PATTERN.matcher(seg).find()) {
            return true;
        } else {
            return COMPLEX_DELIMITER_PATTERN.matcher(seg.substring(0, 1)).find() && seg.charAt(1) == '{' || COMPLEX_DELIMITER_PATTERN.matcher(seg.substring(seg.length() - 1)).find() && seg.charAt(seg.length() - 2) == '}';
        }
    }

    private static List<Segment> parseComplexResourceId(String seg) {
        List<Segment> segments = new ArrayList();
        List<String> separatorIndices = new ArrayList();
        Matcher complexPatternDelimiterMatcher = END_SEGMENT_COMPLEX_DELIMITER_PATTERN.matcher(seg);

        int delimiterIndex;
        for(boolean delimiterFound = complexPatternDelimiterMatcher.find(); delimiterFound; delimiterFound = complexPatternDelimiterMatcher.find(delimiterIndex + 1)) {
            delimiterIndex = complexPatternDelimiterMatcher.start();
            if (seg.substring(delimiterIndex).startsWith("}")) {
                ++delimiterIndex;
            }

            String currDelimiter = seg.substring(delimiterIndex, delimiterIndex + 1);
            if (!COMPLEX_DELIMITER_PATTERN.matcher(currDelimiter).find()) {
                throw new ValidationException("parse error: invalid complex ID delimiter '%s' in '%s'", new Object[]{currDelimiter, seg});
            }

            separatorIndices.add(currDelimiter);
        }

        separatorIndices.add("");
        String subVarName = null;
        Iterable<String> complexSubsegments = Splitter.onPattern("\\}[_\\-\\.~]").trimResults().split(seg);
        boolean complexSegImplicitWildcard = false;
        int currIteratorIndex = 0;

        for(Iterator var9 = complexSubsegments.iterator(); var9.hasNext(); ++currIteratorIndex) {
            String complexSeg = (String)var9.next();
            boolean subsegmentBindingStarts = complexSeg.startsWith("{");
            if (subsegmentBindingStarts) {
                if (subVarName != null) {
                    throw new ValidationException("parse error: nested binding in '%s'", new Object[]{complexSeg});
                }

                complexSeg = complexSeg.substring(1);
            }

            subVarName = complexSeg.trim();
            boolean subBindingEnds = complexSeg.endsWith("}");
            int i = complexSeg.indexOf(61);
            if (i <= 0) {
                if (subBindingEnds) {
                    complexSegImplicitWildcard = true;
                    subVarName = complexSeg.substring(0, complexSeg.length() - 1).trim();
                    complexSeg = complexSeg.substring(complexSeg.length() - 1).trim();
                }
            } else {
                subVarName = complexSeg.substring(0, i).trim();
                complexSeg = complexSeg.substring(i + 1).trim();
                if (complexSeg.equals("**")) {
                    throw new ValidationException("parse error: wildcard path not allowed in complex ID resource '%s'", new Object[]{subVarName});
                }
            }

            String complexDelimiter = currIteratorIndex < separatorIndices.size() ? (String)separatorIndices.get(currIteratorIndex) : "";
            segments.add(PathTemplate.Segment.create(PathTemplate.SegmentKind.BINDING, subVarName, complexDelimiter));
            segments.add(PathTemplate.Segment.wildcardCreate(complexDelimiter));
            segments.add(PathTemplate.Segment.END_BINDING);
            subVarName = null;
        }

        return segments;
    }

    private String encodeUrl(String text) {
        if (this.urlEncoding) {
            try {
                return URLEncoder.encode(text, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                throw new ValidationException("UTF-8 encoding is not supported on this platform", new Object[0]);
            }
        } else {
            String INVALID_CHAR = "/";
            if (text.contains("/")) {
                throw new ValidationException("Invalid character \"/\" in path section \"" + text + "\".", new Object[0]);
            } else {
                return text;
            }
        }
    }

    private String decodeUrl(String url) {
        if (this.urlEncoding) {
            try {
                return URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                throw new ValidationException("UTF-8 encoding is not supported on this platform", new Object[0]);
            }
        } else {
            return url;
        }
    }

    private static boolean peek(ListIterator<Segment> segments, SegmentKind... kinds) {
        int start = segments.nextIndex();
        boolean success = false;
        SegmentKind[] var4 = kinds;
        int var5 = kinds.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            SegmentKind kind = var4[var6];
            if (!segments.hasNext() || ((Segment)segments.next()).kind() != kind) {
                success = false;
                break;
            }
        }

        if (success) {
            return true;
        } else {
            restore(segments, start);
            return false;
        }
    }

    private static void restore(ListIterator<?> segments, int index) {
        while(segments.nextIndex() > index) {
            segments.previous();
        }

    }

    public String toString() {
        return toSyntax(this.segments, true);
    }

    public String toRawString() {
        return toSyntax(this.segments, false);
    }

    private static String toSyntax(List<Segment> segments, boolean pretty) {
        StringBuilder result = new StringBuilder();
        boolean continueLast = true;
        ListIterator<Segment> iterator = segments.listIterator();

        while(true) {
            while(true) {
                while(iterator.hasNext()) {
                    Segment seg = (Segment)iterator.next();
                    if (!continueLast) {
                        result.append(seg.separator());
                    }

                    continueLast = false;
                    switch (seg.kind()) {
                        case END_BINDING:
                            result.append('}');
                            break;
                        case BINDING:
                            if (pretty && seg.value().startsWith("$")) {
                                seg = (Segment)iterator.next();
                                result.append(seg.value());
                                iterator.next();
                            } else {
                                result.append('{');
                                result.append(seg.value());
                                if (pretty && peek(iterator, PathTemplate.SegmentKind.WILDCARD, PathTemplate.SegmentKind.END_BINDING)) {
                                    result.append('}');
                                } else {
                                    result.append('=');
                                    continueLast = true;
                                }
                            }
                            break;
                        default:
                            result.append(seg.value());
                    }
                }

                return result.toString();
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PathTemplate)) {
            return false;
        } else {
            PathTemplate other = (PathTemplate)obj;
            return Objects.equals(this.segments, other.segments);
        }
    }

    public int hashCode() {
        return this.segments.hashCode();
    }

    @AutoValue
    abstract static class Segment {
        private static final Segment WILDCARD;
        private static final Segment PATH_WILDCARD;
        private static final Segment END_BINDING;

        Segment() {
        }

        private static Segment create(SegmentKind kind, String value) {
            return new AutoValue_PathTemplate_Segment(kind, value, "");
        }

        private static Segment create(SegmentKind kind, String value, String complexSeparator) {
            return new AutoValue_PathTemplate_Segment(kind, value, complexSeparator);
        }

        private static Segment wildcardCreate(String complexSeparator) {
            return new AutoValue_PathTemplate_Segment(PathTemplate.SegmentKind.WILDCARD, "*", !complexSeparator.isEmpty() && PathTemplate.COMPLEX_DELIMITER_PATTERN.matcher(complexSeparator).find() ? complexSeparator : "");
        }

        abstract SegmentKind kind();

        abstract String value();

        abstract String complexSeparator();

        boolean isAnyWildcard() {
            return this.kind() == PathTemplate.SegmentKind.WILDCARD || this.kind() == PathTemplate.SegmentKind.PATH_WILDCARD;
        }

        String separator() {
            switch (this.kind()) {
                case CUSTOM_VERB:
                    return ":";
                case END_BINDING:
                    return "";
                default:
                    return "/";
            }
        }

        static {
            WILDCARD = create(PathTemplate.SegmentKind.WILDCARD, "*");
            PATH_WILDCARD = create(PathTemplate.SegmentKind.PATH_WILDCARD, "**");
            END_BINDING = create(PathTemplate.SegmentKind.END_BINDING, "");
        }
    }

    static enum SegmentKind {
        LITERAL,
        CUSTOM_VERB,
        WILDCARD,
        PATH_WILDCARD,
        BINDING,
        END_BINDING;

        private SegmentKind() {
        }
    }
}
