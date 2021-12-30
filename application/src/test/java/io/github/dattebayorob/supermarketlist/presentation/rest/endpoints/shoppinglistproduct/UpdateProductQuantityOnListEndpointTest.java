package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.UpdateProductQuantityInProductListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UpdateProductQuantityOnListEndpointTest {
    @Mock UpdateProductQuantityInProductListService service;
    UpdateProductQuantityOnListEndpoint endpoint;

    @BeforeEach
    void beforeEach() {
        endpoint = new UpdateProductQuantityOnListEndpoint(service);
    }

    @Test
    void shouldReturnAccepted() {
        String listId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        var response = endpoint.putProductOnList(listId, productId, 1);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}