package io.cloudevents.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventBatch;

import java.io.IOException;

class CloudEventBatchSerializer extends JsonSerializer<CloudEventBatch> {

    private final CloudEventSerializer serializer;

    protected CloudEventBatchSerializer(final CloudEventSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void serialize(CloudEventBatch value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (final CloudEvent event : value.getEvents()) {
            serializer.serialize(event, gen, serializers);
        }
        gen.writeEndArray();
    }
}
