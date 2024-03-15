package io.cloudevents.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventBatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class CloudEventBatchDeserializer extends JsonDeserializer<CloudEventBatch> {
    private final CloudEventDeserializer deserializer;

    protected CloudEventBatchDeserializer(final CloudEventDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public CloudEventBatch deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final Collection<CloudEvent> batch = new ArrayList<>();
        try {
            final ArrayNode node = ctxt.readValue(p, ArrayNode.class);
            for (final JsonNode jsonNode : node) {
                batch.add(deserializer.deserialize(jsonNode, p));
            }
        } catch (final InvalidDefinitionException exception) {
            try {
                batch.add(this.deserializer.deserialize(p, ctxt));
            } catch (Exception ignored) {
                throw exception;
            }
        }
        return CloudEventBatch.from(batch);
    }
}
