package io.cloudevents.core.data;

import io.cloudevents.CloudEventData;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * An implementation of {@link CloudEventData} that wraps a byte array.
 */
public class ByteBufferCloudEventData implements CloudEventData {

    private final ByteBuffer value;

    /**
     * @param value the bytes to wrap
     */
    private ByteBufferCloudEventData(ByteBuffer value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public synchronized byte[] toBytes() {
        final ByteBuffer value = this.value.duplicate();
        final byte[] bytes = new byte[value.remaining()];
        value.get(bytes);
        return bytes;
    }

    @Override
    public ByteBuffer toByteBuffer() {
        return this.value.asReadOnlyBuffer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteBufferCloudEventData that = (ByteBufferCloudEventData) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ByteBufferCloudEventData{" +
            "value=" + value +
            '}';
    }

    /**
     * @param value byte array to wrap
     * @return byte array wrapped in a {@link ByteBufferCloudEventData}, which implements {@link CloudEventData}.
     */
    public static ByteBufferCloudEventData wrap(ByteBuffer value) {
        return new ByteBufferCloudEventData(value);
    }
}
