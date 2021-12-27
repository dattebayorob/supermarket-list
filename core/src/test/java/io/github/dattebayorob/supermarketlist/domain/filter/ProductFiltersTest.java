package io.github.dattebayorob.supermarketlist.domain.filter;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductFiltersTest {
    @Test
    void shouldCreateFilters() {
        String name = "";
        String categoryId = "";

        var expected = new ProductFilters("", null);
        var filters = new ProductFilters(null, "");
        assertEquals(expected, filters);
        UUID id = UUID.randomUUID();
        assertNotNull(new ProductFilters("", id.toString()));
    }
}