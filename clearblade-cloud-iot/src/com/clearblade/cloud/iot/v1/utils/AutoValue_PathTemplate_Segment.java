package com.clearblade.cloud.iot.v1.utils;

final class AutoValue_PathTemplate_Segment extends PathTemplate.Segment {
    private final PathTemplate.SegmentKind kind;
    private final String value;
    private final String complexSeparator;

    AutoValue_PathTemplate_Segment(PathTemplate.SegmentKind kind, String value, String complexSeparator) {
        if (kind == null) {
            throw new NullPointerException("Null kind");
        } else {
            this.kind = kind;
            if (value == null) {
                throw new NullPointerException("Null value");
            } else {
                this.value = value;
                if (complexSeparator == null) {
                    throw new NullPointerException("Null complexSeparator");
                } else {
                    this.complexSeparator = complexSeparator;
                }
            }
        }
    }

    PathTemplate.SegmentKind kind() {
        return this.kind;
    }

    String value() {
        return this.value;
    }

    String complexSeparator() {
        return this.complexSeparator;
    }

    public String toString() {
        return "Segment{kind=" + this.kind + ", value=" + this.value + ", complexSeparator=" + this.complexSeparator + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PathTemplate.Segment)) {
            return false;
        } else {
            PathTemplate.Segment that = (PathTemplate.Segment)o;
            return this.kind.equals(that.kind()) && this.value.equals(that.value()) && this.complexSeparator.equals(that.complexSeparator());
        }
    }

    public int hashCode() {
        int h$ = 1;
        h$ *= 1000003;
        h$ ^= this.kind.hashCode();
        h$ *= 1000003;
        h$ ^= this.value.hashCode();
        h$ *= 1000003;
        h$ ^= this.complexSeparator.hashCode();
        return h$;
    }
}