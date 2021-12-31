package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.CheckProductInListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CheckProductInListEndpointTest {
    @Mock CheckProductInListService checkProductInListService;
    CheckProductInListEndpoint checkProductInListEndpoint;

    @BeforeEach
    void beforeEach() {
        checkProductInListEndpoint = new CheckProductInListEndpoint(checkProductInListService);
    }

    @Test
    void shouldReturnAccepted() {
        String shoppingListId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        var response = checkProductInListEndpoint.check(shoppingListId, productId);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}