package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product;

import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

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
        var filters = new ProductFilters(null, null, null,0, 10, null);
        when(findProductsService.findAll(filters)).thenReturn(Pagination.empty());

        var response = findProductEndpoint.findAll(null, null, null,0, 10, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}