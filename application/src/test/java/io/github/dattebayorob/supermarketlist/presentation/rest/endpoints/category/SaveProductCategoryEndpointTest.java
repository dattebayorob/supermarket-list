package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.SaveProductCategoryService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveProductCategoryEndpointTest {
    @Mock ProductCategory domain;
    @Mock ProductCategoryResponse categoryResponse;
    @Mock ProductCategoryRequest categoryRequest;
    @Mock SaveProductCategoryService saveProductCategoryService;
    @Mock ProductCategoryMapper productCategoryMapper;
    SaveProductCategoryEndpoint endpoint;

    @BeforeEach
    void beforeEach() {
        endpoint = new SaveProductCategoryEndpoint(saveProductCategoryService, productCategoryMapper);
    }

    @Test
    void shouldReturnCreatedWithCategoryResponseAndUri() {
        String id = UUID.randomUUID().toString();
        when(categoryResponse.getId()).thenReturn(id);
        when(productCategoryMapper.toDomain(categoryRequest)).thenReturn(domain);
        when(productCategoryMapper.toResponse(domain)).thenReturn(categoryResponse);
        when(saveProductCategoryService.save(domain)).thenReturn(domain);

        var response = endpoint.saveCategoy(categoryRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryResponse, response.getBody());
        assertEquals("/v1/categories/"+id, response.getHeaders().getFirst("Location"));
    }
}