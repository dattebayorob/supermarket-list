package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product;

import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginatedProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsByProductCategoryIdEndpointTest {
    @Mock FindProductEndpoint findProductEndpoint;
    ProductsByProductCategoryIdEndpoint productsByProductCategoryIdEndpoint;

    @BeforeEach
    void beforeEach() {
        productsByProductCategoryIdEndpoint = new ProductsByProductCategoryIdEndpoint(findProductEndpoint);
    }

    @Test
    void shouldDeleateToFindProductEndpoint() {
        String name = null;
        String categoryId = UUID.randomUUID().toString();
        when(findProductEndpoint.findAll(name, categoryId, null, 0, 0, null))
                .thenReturn(ResponseEntity.ok().body(new PaginatedProductsResponse().content(Collections.emptyList())));
        var response = productsByProductCategoryIdEndpoint.findAllByCategoryId(categoryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(findProductEndpoint).findAll(name, categoryId, null, 0, 0, null);
    }

}