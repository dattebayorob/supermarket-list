package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductCategoryJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryRepositoryTest {
    static final String CATEGORY_NAME = "Drinks";
    @Mock ProductCategoryJpaRepository productCategoryJpaRepository;
    @Mock ProductCategoryMapper productCategoryMapper;
    ProductCategoryRepositoryImpl productCategoryRepository;

    @BeforeEach
    void beforeEach() {
        productCategoryRepository = new ProductCategoryRepositoryImpl(productCategoryJpaRepository, productCategoryMapper);
    }

    @Test
    void shouldReturnTrueIfExistsByName() {
        when(productCategoryJpaRepository.existsByName(CATEGORY_NAME)).thenReturn(true);
        Assertions.assertTrue(productCategoryRepository.existsByName(CATEGORY_NAME));
    }

    @Test
    void shouldReturnTrueIfExistsByNameAndIdIsNotEqualsToOtherParameter() {
        UUID id = UUID.randomUUID();
        when(productCategoryJpaRepository.existsByNameAndIdNot(CATEGORY_NAME, id)).thenReturn(true);
        Assertions.assertTrue(productCategoryRepository.existsByNameAndIdNotIn(CATEGORY_NAME, id));
    }

    @Test
    void shouldReturnDomainRepresentationOfCategoryByName() {
        var entities = Arrays.asList(new ProductCategoryJpa());
        var domains = Arrays.asList(new ProductCategory());
        when(productCategoryJpaRepository.findByNameContainingIgnoreCase(CATEGORY_NAME)).thenReturn(entities);
        when(productCategoryMapper.toDomain(entities)).thenReturn(domains);
        assertThat(productCategoryRepository.findByNameLike(CATEGORY_NAME)).isNotEmpty().isEqualTo(domains);
    }

    @Test
    void shouldSaveCategory() {
        var domain = new ProductCategory();
        var entity = new ProductCategoryJpa();
        when(productCategoryMapper.toEntity(domain)).thenReturn(entity);
        when(productCategoryJpaRepository.save(entity)).thenReturn(entity);
        when(productCategoryMapper.toDomain(entity)).thenReturn(domain);
        assertEquals(domain, productCategoryRepository.save(domain));

        var inOrder = inOrder(productCategoryJpaRepository, productCategoryMapper);
        inOrder.verify(productCategoryMapper).toEntity(domain);
        inOrder.verify(productCategoryJpaRepository).save(entity);
        inOrder.verify(productCategoryMapper).toDomain(entity);
    }

    @Test
    void shouldFindById() {
        UUID id = UUID.randomUUID();
        var entity = new ProductCategoryJpa();
        var domain = new ProductCategory();
        when(productCategoryJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(productCategoryMapper.toDomain(entity)).thenReturn(domain);
        assertThat(productCategoryRepository.findById(id)).isNotEmpty().contains(domain);
    }
}
