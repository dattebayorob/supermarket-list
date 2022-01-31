package io.github.dattebayorob.supermarketlist.domain.filter;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingListFiltersTest {
    @Test
    void shouldCreateWithDefaultSort() {
        var after = LocalDateTime.now();
        var before = LocalDateTime.now();
        var filters = new ShoppingListFilters(after, before, 0, 10, null);
        assertThat(filters.getSort()).isEqualTo(List.of("createdAt,desc"));
        var filters2 = new ShoppingListFilters(after, before, null, null, null);
        assertThat(filters).isEqualTo(filters2);
    }
}