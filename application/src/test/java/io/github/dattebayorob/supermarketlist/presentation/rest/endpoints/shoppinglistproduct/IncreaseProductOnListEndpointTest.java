package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.IncreaseProductQuantityInProductListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IncreaseProductOnListEndpointTest {
    @Mock IncreaseProductQuantityInProductListService increaseProductQuantityInProductListService;
    IncreaseProductOnListEndpoint increaseProductOnListEndpoint;

    @BeforeEach
    void beforeEach() {
        increaseProductOnListEndpoint = new IncreaseProductOnListEndpoint(increaseProductQuantityInProductListService);
    }

    @Test
    void shouldReturnAccepted() {
        var productId = UUID.randomUUID();
        var shoppingListID = UUID.randomUUID();

        var response = increaseProductOnListEndpoint.increaseProductsOnList(shoppingListID.toString(), productId.toString());

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(increaseProductQuantityInProductListService).increaseProductQuantityInProductList(productId, shoppingListID);
    }
}