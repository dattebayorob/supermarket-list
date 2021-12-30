package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductEndpointTest {
    @Mock FindProductsService findProductsService;
    @Mock ProductMapper productMapper;
    FindProductEndpoint findProductEndpoint;

    @BeforeEach
    void beforeEach() {
        findProductEndpoint = new FindProductEndpoint(findProductsService, productMapper);
    }

    @Test
    void shouldReturnStatusOkWithProducts() {
        var products = new ArrayList<Product>();
        var responses = new ArrayList<ProductResponse>();
        var filters = new ProductFilters(null, null);
        when(findProductsService.findAll(filters)).thenReturn(products);
        when(productMapper.toResponse(products)).thenReturn(responses);

        var response = findProductEndpoint.findAll(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}