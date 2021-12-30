package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductSelectionMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsByShoppingListIdService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductsByShoppingListEndpointTest {
    @Mock FindProductsByShoppingListIdService findProductsByShoppingListIdService;
    @Mock ProductSelectionMapper productSelectionMapper;
    FindProductsByShoppingListEndpoint findProductsByShoppingListEndpoint;

    @BeforeEach
    void beforeEach() {
        findProductsByShoppingListEndpoint = new FindProductsByShoppingListEndpoint(findProductsByShoppingListIdService, productSelectionMapper);
    }

    @Test
    void shouldReturnAccepted() {
        UUID shoppingListId = UUID.randomUUID();
        var selections = Arrays.asList(new ProductSelection());
        var responses = Arrays.asList(new ProductSelectionResponse());

        when(findProductsByShoppingListIdService.findProductsByShoppingListId(shoppingListId))
                .thenReturn(selections);
        when(productSelectionMapper.toResponse(selections)).thenReturn(responses);

        var response = findProductsByShoppingListEndpoint.findByProductListId(shoppingListId.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
        verify(findProductsByShoppingListIdService).findProductsByShoppingListId(shoppingListId);
        verify(productSelectionMapper).toResponse(selections);

    }

}