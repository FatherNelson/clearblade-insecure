package com.clearblade.cloud.iot.v1.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TemplatedResourceName implements Map<String, String> {
    private static volatile Resolver resourceNameResolver = new Resolver() {
        public <T> T resolve(Class<T> resourceType, TemplatedResourceName name, String version) {
            throw new IllegalStateException("No resource name resolver is registered in ResourceName class.");
        }
    };
    private final PathTemplate template;
    private final ImmutableMap<String, String> values;
    private final String endpoint;
    private volatile String stringRepr;

    public static void registerResourceNameResolver(Resolver resolver) {
        resourceNameResolver = resolver;
    }

    public static TemplatedResourceName create(PathTemplate template, String path) {
        Map<String, String> values = template.match(path);
        if (values == null) {
            throw new ValidationException("path '%s' does not match template '%s'", new Object[]{path, template});
        } else {
            return new TemplatedResourceName(template, values, (String)null);
        }
    }

    public static TemplatedResourceName create(PathTemplate template, Map<String, String> values) {
        if (!values.keySet().containsAll(template.vars())) {
            Set<String> unbound = Sets.newLinkedHashSet(template.vars());
            unbound.removeAll(values.keySet());
            throw new ValidationException("unbound variables: %s", new Object[]{unbound});
        } else {
            return new TemplatedResourceName(template, values, (String)null);
        }
    }

    @Nullable
    public static TemplatedResourceName createFromFullName(PathTemplate template, String path) {
        Map<String, String> values = template.matchFromFullName(path);
        return values == null ? null : new TemplatedResourceName(template, values, (String)null);
    }

    private TemplatedResourceName(PathTemplate template, Map<String, String> values, String endpoint) {
        this.template = template;
        this.values = ImmutableMap.copyOf(values);
        this.endpoint = endpoint;
    }

    public String toString() {
        if (this.stringRepr == null) {
            this.stringRepr = this.template.instantiate(this.values);
        }

        return this.stringRepr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TemplatedResourceName)) {
            return false;
        } else {
            TemplatedResourceName other = (TemplatedResourceName)obj;
            return Objects.equals(this.template, other.template) && Objects.equals(this.endpoint, other.endpoint) && Objects.equals(this.values, other.values);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.template, this.endpoint, this.values});
    }

    public PathTemplate template() {
        return this.template;
    }

    public boolean hasEndpoint() {
        return this.endpoint != null;
    }

    @Nullable
    public String endpoint() {
        return this.endpoint;
    }

    public TemplatedResourceName withEndpoint(String endpoint) {
        return new TemplatedResourceName(this.template, this.values, (String) Preconditions.checkNotNull(endpoint));
    }

    public TemplatedResourceName parentName() {
        PathTemplate parentTemplate = this.template.parentTemplate();
        return new TemplatedResourceName(parentTemplate, this.values, this.endpoint);
    }

    public boolean startsWith(TemplatedResourceName parentName) {
        return this.toString().startsWith(parentName.toString());
    }

    public <T> T resolve(Class<T> resourceType, @Nullable String version) {
        Preconditions.checkArgument(this.hasEndpoint(), "Resource name must have an endpoint.");
        return resourceNameResolver.resolve(resourceType, this, version);
    }

    public int size() {
        return this.values.size();
    }

    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.values.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.values.containsValue(value);
    }

    public String get(Object key) {
        return (String)this.values.get(key);
    }

    /** @deprecated */
    @Deprecated
    public String put(String key, String value) {
        return (String)this.values.put(key, value);
    }

    /** @deprecated */
    @Deprecated
    public String remove(Object key) {
        return (String)this.values.remove(key);
    }

    /** @deprecated */
    @Deprecated
    public void putAll(Map<? extends String, ? extends String> m) {
        this.values.putAll(m);
    }

    /** @deprecated */
    @Deprecated
    public void clear() {
        this.values.clear();
    }

    public Set<String> keySet() {
        return this.values.keySet();
    }

    public Collection<String> values() {
        return this.values.values();
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return this.values.entrySet();
    }

    public interface Resolver {
        <T> T resolve(Class<T> var1, TemplatedResourceName var2, @Nullable String var3);
    }
}