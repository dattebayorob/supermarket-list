package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductSelectionMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.out.SaveShoppingListService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveShoppingListEndpointTest {
    @Mock ProductSelectionRequest productSelectionRequest;
    @Mock ShoppingListResponse shoppingListResponse;
    @Mock ProductSelection productSelection;
    @Mock ShoppingList shoppingList;

    @Mock SaveShoppingListService saveShoppingListService;
    @Mock ShoppingListMapper shoppingListMapper;
    @Mock ProductSelectionMapper productSelectionMapper;
    SaveShoppingListEndpoint endpoint;

    @BeforeEach
    void beforeEach() {
        endpoint = new SaveShoppingListEndpoint(saveShoppingListService, shoppingListMapper, productSelectionMapper);
    }

    @Test
    void shouldSaveProductList() {
        UUID id = UUID.randomUUID();
        var request = new ShoppingListRequest()
                .products(Arrays.asList(productSelectionRequest));
        when(productSelectionMapper.toDomain(productSelectionRequest)).thenReturn(productSelection);
        when(saveShoppingListService.save(anyList())).thenReturn(shoppingList);
        when(shoppingList.getId()).thenReturn(id);
        when(shoppingListMapper.toResponse(shoppingList)).thenReturn(shoppingListResponse);

        var response = endpoint.save(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(shoppingListResponse, response.getBody());
    }
}