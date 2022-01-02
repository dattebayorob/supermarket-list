package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.out.CloneShoppingListService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CloneShoppingListEndpointTest {
    @Mock ShoppingList shoppingList;
    @Mock ShoppingListResponse shoppingListResponse;
    @Mock CloneShoppingListService cloneShoppingListService;
    @Mock ShoppingListMapper shoppingListMapper;
    CloneShoppingListEndpoint cloneShoppingListEndpoint;

    @BeforeEach
    void beforeEach() {
        cloneShoppingListEndpoint = new CloneShoppingListEndpoint(cloneShoppingListService, shoppingListMapper);
    }

    @Test
    void shouldReturnStatusCreatedWithClonedShoppingList() {
        UUID shoppingListId = UUID.randomUUID();
        UUID clonedShoppingListId = UUID.randomUUID();
        when(cloneShoppingListService.clone(clonedShoppingListId)).thenReturn(Optional.of(shoppingList));
        when(shoppingListMapper.toResponse(shoppingList)).thenReturn(shoppingListResponse);
        when(shoppingListResponse.getId()).thenReturn(shoppingListId.toString());

        var response = cloneShoppingListEndpoint.clone(clonedShoppingListId.toString());
        assertEquals("/v1/lists/"+shoppingListId, response.getHeaders().getFirst("Location"));
        assertEquals(shoppingListResponse, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundIfClonedShoppingListDoesNotExist() {
        UUID clonedShoppingListId = UUID.randomUUID();
        when(cloneShoppingListService.clone(clonedShoppingListId)).thenReturn(Optional.empty());

        var response = cloneShoppingListEndpoint.clone(clonedShoppingListId.toString());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}