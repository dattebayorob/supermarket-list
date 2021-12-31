package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.UncheckProductInListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UncheckProductInListEndpointTest {
    @Mock UncheckProductInListService uncheckProductInListService;
    UncheckProductInListEndpoint uncheckProductInListEndpoint;

    @BeforeEach
    void beforeEach() {
        uncheckProductInListEndpoint = new UncheckProductInListEndpoint(uncheckProductInListService);
    }

    @Test
    void shouldReturnAccepted() {
        String shoppingListId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        var response = uncheckProductInListEndpoint.unCheck(shoppingListId, productId);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}