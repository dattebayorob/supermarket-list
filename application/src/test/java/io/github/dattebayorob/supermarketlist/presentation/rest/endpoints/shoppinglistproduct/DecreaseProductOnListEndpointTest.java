package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.DecreaseProductQuantityInProductListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DecreaseProductOnListEndpointTest {
    @Mock DecreaseProductQuantityInProductListService decreaseProductQuantityInProductListService;
    DecreaseProductOnListEndpoint decreaseProductOnListEndpoint;

    @BeforeEach
    void beforeEach() {
        decreaseProductOnListEndpoint = new DecreaseProductOnListEndpoint(decreaseProductQuantityInProductListService);
    }

    @Test
    void shouldReturnAccepted() {
        String shoppingListId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        assertTrue(decreaseProductOnListEndpoint.decreaseProductsOnList(shoppingListId, productId).getStatusCode().equals(HttpStatus.ACCEPTED));
        verify(decreaseProductQuantityInProductListService).decreaseProductQuantityInProductList(any(), any());
    }
}