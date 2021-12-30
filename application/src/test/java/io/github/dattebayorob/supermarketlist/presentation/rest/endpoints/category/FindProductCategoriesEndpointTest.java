package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductCategoriesByNameLikeService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
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
class FindProductCategoriesEndpointTest {
    @Mock FindProductCategoriesByNameLikeService findProductCategoriesByNameLikeService;
    @Mock ProductCategoryMapper productCategoryMapper;
    FindProductCategoriesEndpoint findProductCategoriesEndpoint;

    @BeforeEach
    void beforeEach() {
        findProductCategoriesEndpoint = new FindProductCategoriesEndpoint(findProductCategoriesByNameLikeService, productCategoryMapper);
    }

    @Test
    void shouldReturnOkWithCategoriesInResponse() {
        var categories = new ArrayList<ProductCategory>();
        var responses = new ArrayList<ProductCategoryResponse>();
        String name = "Name";

        when(findProductCategoriesByNameLikeService.findByNameLike(name))
                .thenReturn(categories);
        when(productCategoryMapper.toResponse(categories)).thenReturn(responses);
        var response = findProductCategoriesEndpoint.findCategoriesByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
    }
}