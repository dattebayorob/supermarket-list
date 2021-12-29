package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveProductCategoryServiceImplTest {
    @Mock ProductCategoryRepository productCategoryRepository;
    SaveProductCategoryServiceImpl saveProductCategoryService;

    @BeforeEach
    void beforeEach() {
        saveProductCategoryService = new SaveProductCategoryServiceImpl(productCategoryRepository);
    }

    @Test
    void shouldThrowsBusinessExceptionIfNameAlreadyExists(){
        var category = getCategory();
        when(productCategoryRepository.existsByName(category.getName())).thenReturn(true);

        Assertions.assertThatThrownBy(() -> saveProductCategoryService.save(category))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Nome já utilizado por outra categoria de produto!");
        Mockito.verify(productCategoryRepository, never()).save(category);
    }

    @Test
    void shouldSaveCategoryIfNameDoesntExist() {
        var category = getCategory();
        when(productCategoryRepository.existsByName(category.getName())).thenReturn(false);

        Assertions.assertThatCode(() -> saveProductCategoryService.save(category))
                .doesNotThrowAnyException();
        Mockito.verify(productCategoryRepository).save(category);
    }

    ProductCategory getCategory() {
        var category = new ProductCategory();
        category.setName("NAME");
        return category;
    }
}