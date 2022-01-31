package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindShoppingListsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindShoppingLitsBetweenDatesEndpointTest {
    @Mock FindShoppingListsService findShoppingListsService;
    @Mock ShoppingListMapper shoppingListMapper;
    @Mock PaginationMapper paginationMapper;
    FindShoppingLitsBetweenDatesEndpoint endpoint;

    @BeforeEach
    void beforeEach() {
        endpoint = new FindShoppingLitsBetweenDatesEndpoint(findShoppingListsService, shoppingListMapper, paginationMapper);
    }

    @Test
    void shouldFindShoppingListsByFilters() {
        var after = LocalDateTime.now();
        var before = LocalDateTime.now();
        var filters = new ShoppingListFilters(after, before, null, null, null);
        var expected = Pagination.<ShoppingList>empty();

        when(findShoppingListsService.findAll(filters)).thenReturn(expected);
        when(paginationMapper.toResponse(expected)).thenReturn(new PaginationResponse());

        var response = endpoint.findAll(after.atOffset(ZoneOffset.UTC), before.atOffset(ZoneOffset.UTC), null, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}