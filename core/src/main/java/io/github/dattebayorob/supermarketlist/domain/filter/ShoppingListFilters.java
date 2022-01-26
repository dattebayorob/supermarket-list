package io.github.dattebayorob.supermarketlist.domain.filter;

import io.github.dattebayorob.supermarketlist.domain.util.PageFilter;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShoppingListFilters extends PageFilter {
    private LocalDateTime after;
    private LocalDateTime before;
    public ShoppingListFilters(
        LocalDateTime after,
        LocalDateTime before,
        Integer page,
        Integer size,
        String sort
    ) {
        this.after = after;
        this.before = before;
        setPage(page);
        setSize(size);
        setSort(sort);
    }
}
