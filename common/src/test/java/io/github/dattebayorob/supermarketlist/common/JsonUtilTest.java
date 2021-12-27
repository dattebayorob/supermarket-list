package io.github.dattebayorob.supermarketlist.common;

import lombok.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTest {
    @Test
    void shouldSerializeToJsonString() {
        var representation = new Representation(1L, "Name");
        String json = "{\"id\":1,\"name\":\"Name\"}";
        assertEquals(json, JsonUtil.serialize(representation));
    }
    @Test
    void shouldDeserializeToObject() {
        var representation = new Representation(1L, "Name");
        String json = "{\"id\":1,\"name\":\"Name\"}";
        assertEquals(representation, JsonUtil.deserialize(json, Representation.class));
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Representation {
        private Long id;
        private String name;

    }
}