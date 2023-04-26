package io.cloudevents.core.data;

import io.cloudevents.CloudEventData;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * An implementation of {@link CloudEventData} that wraps a String.
 */
public class StringCloudEventData implements CloudEventData {

    private final String value;

    /**
     * @param value the bytes to wrap
     */
    StringCloudEventData(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public byte[] toBytes() {
        return this.value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringCloudEventData that = (StringCloudEventData) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "StringCloudEventData{" +
            "value=" + value +
            '}';
    }

    /**
     * @param value byte array to wrap
     * @return byte array wrapped in a {@link StringCloudEventData}, which implements {@link CloudEventData}.
     */
    public static StringCloudEventData wrap(String value) {
        return new StringCloudEventData(value);
    }
}
