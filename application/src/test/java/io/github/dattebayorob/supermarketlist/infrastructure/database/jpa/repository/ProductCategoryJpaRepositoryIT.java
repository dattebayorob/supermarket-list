package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductCategoryJpaRepositoryIT {
    static final UUID CATEGORY_ID = UUID.fromString("343ef472-8e6a-493a-bfb1-252bc1802ee7");
    @Autowired ProductCategoryJpaRepository repository;

    @Test
    public void shouldFetchAllProductCategories() {
        ProductCategoryJpa drinks = new ProductCategoryJpa();
        drinks.setId(CATEGORY_ID);
        List<ProductCategoryJpa> categories = repository.findByNameContainingIgnoreCase("");
        assertThat(categories).isNotEmpty().contains(drinks);
    }
    @Test
    public void shouldFindProductCategoriesByName() {
        ProductCategoryJpa drinks = new ProductCategoryJpa();
        drinks.setId(CATEGORY_ID);
        List<ProductCategoryJpa> categories = repository.findByNameContainingIgnoreCase("bebidas");
        assertThat(categories).hasSize(1).contains(drinks);
    }
}