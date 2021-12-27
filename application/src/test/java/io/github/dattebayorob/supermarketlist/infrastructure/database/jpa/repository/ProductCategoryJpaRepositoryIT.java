package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles({ "dev", "in-memory" })
class ProductCategoryJpaRepositoryIT {
    static final UUID CATEGORY_ID = UUID.fromString("343ef472-8e6a-493a-bfb1-252bc1802ee7");
    @Autowired ProductCategoryJpaRepository repository;

    @Test
    void shouldFetchAllProductCategories() {
        ProductCategoryJpa drinks = new ProductCategoryJpa();
        drinks.setId(CATEGORY_ID);
        List<ProductCategoryJpa> categories = repository.findByNameContainingIgnoreCase("");
        assertThat(categories).isNotEmpty().contains(drinks);
    }
    @Test
    void shouldFindProductCategoriesByName() {
        ProductCategoryJpa drinks = new ProductCategoryJpa();
        drinks.setId(CATEGORY_ID);
        List<ProductCategoryJpa> categories = repository.findByNameContainingIgnoreCase("bebidas");
        assertThat(categories).hasSize(1).contains(drinks);
    }
    @Test
    void shouldReturnTrueIfProductCategoryExistsById() {
        Assertions.assertTrue(repository.existsById(CATEGORY_ID));
    }
    @Test
    void shouldReturnTrueIfAlreadyExistsByName() {
        String name = "Some category "+new Random().nextInt();
        ProductCategoryJpa category = new ProductCategoryJpa();
        category.setName(name);
        repository.save(category);
        Assertions.assertTrue(repository.existsByName(name));
    }
    @Test
    void shouldReturnFalseIfAlreadyExistsByNameButIdsAreDiferents() {
        String name = "Some category "+new Random().nextInt();
        ProductCategoryJpa category = new ProductCategoryJpa();
        category.setName(name);
        repository.save(category);

        Assertions.assertFalse(repository.existsByNameAndIdNot(name, category.getId()));
    }
}