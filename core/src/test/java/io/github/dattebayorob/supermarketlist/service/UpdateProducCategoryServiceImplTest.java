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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateProducCategoryServiceImplTest {
    @Mock
    ProductCategoryRepository productCategoryRepository;
    UpdateProducCategoryServiceImpl updateProducCategoryService;

    @BeforeEach
    void beforeEach() {
        updateProducCategoryService = new UpdateProducCategoryServiceImpl(productCategoryRepository);
    }

    @Test
    void shouldThrowsBusinessExceptionIfNameAlreadyExists(){
        var category = getCategory();
        when(productCategoryRepository.existsByNameAndIdNotIn(category.getName(), category.getId())).thenReturn(true);

        Assertions.assertThatThrownBy(() -> updateProducCategoryService.update(category))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Nome já utilizado por outra categoria de produto!");
        Mockito.verify(productCategoryRepository, never()).save(category);
    }

    @Test
    void shouldUpdateCategoryIfNameDoesntExist() {
        var category = getCategory();
        when(productCategoryRepository.existsByNameAndIdNotIn(category.getName(), category.getId())).thenReturn(false);
        when(productCategoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        Assertions.assertThatCode(() -> updateProducCategoryService.update(category))
                .doesNotThrowAnyException();
        Mockito.verify(productCategoryRepository).save(category);
    }

    ProductCategory getCategory() {
        var category = new ProductCategory();
        category.setId(UUID.randomUUID());
        category.setName("NAME");
        return category;
    }
}