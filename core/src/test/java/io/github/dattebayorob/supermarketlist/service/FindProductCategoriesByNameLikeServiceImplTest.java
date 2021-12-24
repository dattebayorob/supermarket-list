package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductCategoriesByNameLikeServiceImplTest {
    @Mock ProductCategoryRepository productCategoryRepository;
    FindProductCategoriesByNameLikeServiceImpl findProductCategoriesByNameLikeService;

    @BeforeEach
    void beforeEach() {
        findProductCategoriesByNameLikeService = new FindProductCategoriesByNameLikeServiceImpl(productCategoryRepository);
    }

    @Test
    void shouldFindProductCategoriesByName() {
        String name = "Bebidas";
        List<ProductCategory> expectedCategories = Arrays.asList(new ProductCategory());
        when(productCategoryRepository.findByNameLike(name)).thenReturn(expectedCategories);

        List<ProductCategory> categories = findProductCategoriesByNameLikeService.findByNameLike(name);

        assertThat(categories).isNotEmpty().isEqualTo(expectedCategories);
    }

    @Test
    void shouldSearchProductCategoriesByEmptyStringWhenNameIsNull() {
        String name = null;
        String emptyString = "";
        List<ProductCategory> expectedCategories = Arrays.asList(new ProductCategory());
        when(productCategoryRepository.findByNameLike(emptyString)).thenReturn(expectedCategories);

        List<ProductCategory> categories = findProductCategoriesByNameLikeService.findByNameLike(name);

        assertThat(categories).isNotEmpty().isEqualTo(expectedCategories);
        verify(productCategoryRepository).findByNameLike(emptyString);
    }
}