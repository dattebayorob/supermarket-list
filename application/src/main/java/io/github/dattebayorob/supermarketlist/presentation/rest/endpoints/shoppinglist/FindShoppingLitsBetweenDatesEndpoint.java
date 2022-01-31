package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist;

import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindShoppingListsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindShoppingLitsBetweenDatesEndpoint {
    private final FindShoppingListsService findShoppingListsService;
    private final ShoppingListMapper shoppingListMapper;
    private final PaginationMapper paginationMapper;

    public ResponseEntity<PaginationResponse> findAll(OffsetDateTime after, OffsetDateTime before, Integer page, Integer size, List<String> sort) {
        var filters = new ShoppingListFilters(toLocalDateTime(after), toLocalDateTime(before), page, size, sort);
        var pagination = findShoppingListsService.findAll(filters).map(shoppingListMapper::toResponse);
        return ResponseEntity.ok(paginationMapper.toResponse(pagination));
    }

    private LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime).map(OffsetDateTime::toLocalDateTime).orElse(null);
    }
}
