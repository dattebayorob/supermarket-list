package io.github.dattebayorob.supermarketlist.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    @Test
    public void shouldSerializeToJsonString() {
        var representation = new Representation(1L, "Name");
        String json = "{\"id\":1,\"name\":\"Name\"}";
        assertEquals(json, JsonUtil.serialize(representation));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Representation {
        private Long id;
        private String name;

    }
}