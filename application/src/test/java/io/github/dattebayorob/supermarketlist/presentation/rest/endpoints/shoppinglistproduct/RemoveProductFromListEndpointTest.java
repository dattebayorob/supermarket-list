package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.RemoveProductFromShoppingListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RemoveProductFromListEndpointTest {
    @Mock RemoveProductFromShoppingListService removeProductFromShoppingListService;
    RemoveProductFromListEndpoint removeProductFromListEndpoint;

    @BeforeEach
    void beforeEach() {
        removeProductFromListEndpoint = new RemoveProductFromListEndpoint(removeProductFromShoppingListService);
    }

    @Test
    void shouldReturnNoContent() {
        UUID productId = UUID.randomUUID();
        UUID listId = UUID.randomUUID();

        var response = removeProductFromListEndpoint.removeProductOnList(listId.toString(), productId.toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(removeProductFromShoppingListService).removeProductFromShoppingList(productId, listId);
    }
}