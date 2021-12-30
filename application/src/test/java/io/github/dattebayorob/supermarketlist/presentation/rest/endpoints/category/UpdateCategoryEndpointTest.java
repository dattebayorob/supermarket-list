package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.UpdateProductCategoryService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryEndpointTest {
    @Mock ProductCategory domain;
    @Mock ProductCategoryRequest categoryRequest;
    @Mock ProductCategoryResponse categoryResponse;
    @Mock UpdateProductCategoryService updateProductCategoryService;
    @Mock ProductCategoryMapper productCategoryMapper;
    UpdateCategoryEndpoint updateCategoryEndpoint;

    @BeforeEach
    void beforeEach() {
        updateCategoryEndpoint = new UpdateCategoryEndpoint(updateProductCategoryService, productCategoryMapper);
        when(productCategoryMapper.toDomain(categoryRequest)).thenReturn(domain);
    }

    @Test
    void shouldReturnNotFoundIfCategoryDoesntExist() {
        UUID id = UUID.randomUUID();
        when(updateProductCategoryService.update(domain)).thenReturn(Optional.empty());
        assertEquals(HttpStatus.NOT_FOUND, updateCategoryEndpoint.updateCategory(id.toString(), categoryRequest).getStatusCode());
    }

    @Test
    void shouldReturnOkWhenCategoryExists() {
        UUID id = UUID.randomUUID();
        when(updateProductCategoryService.update(domain)).thenReturn(Optional.of(domain));
        when(productCategoryMapper.toResponse(domain)).thenReturn(categoryResponse);
        var response = updateCategoryEndpoint.updateCategory(id.toString(), categoryRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponse, response.getBody());
    }
}