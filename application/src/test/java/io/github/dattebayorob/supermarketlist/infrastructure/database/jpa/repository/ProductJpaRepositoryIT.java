package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles({ "dev", "in-memory" })
class ProductJpaRepositoryIT {
    @Autowired ShoppingListJpaRepository shoppingListJpaRepository;
    @Autowired ProductJpaRepository productJpaRepository;
    @Autowired ProductCategoryJpaRepository productCategoryJpaRepository;
    @Autowired ProductSelectionJpaRepository productSelectionJpaRepository;

    @Test
    void shouldFindProductsByShoppingListId() {
        var category = productCategoryJpaRepository.save(getCategory());
        var products = productJpaRepository.saveAll(getProducts(category, "Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6"));
        var products2 = productJpaRepository.saveAll(getProducts(category, "Product 7", "Product 8", "Product 9", "Product 10"));
        var shoppingList = shoppingListJpaRepository.save(getShoppingList(getDate(Month.JANUARY)));
        var shoppingList2 = shoppingListJpaRepository.save(getShoppingList(getDate(Month.FEBRUARY)));

        productSelectionJpaRepository.saveAll(mapSelections(products, shoppingList.getId()));
        productSelectionJpaRepository.saveAll(mapSelections(products2, shoppingList2.getId()));

        assertThat(productJpaRepository.findByShoppingListId(shoppingList.getId())).hasSize(6);
        assertThat(productJpaRepository.findByShoppingListId(shoppingList2.getId())).hasSize(4);

    }

    List<ProductSelectionJpa> mapSelections(List<ProductJpa> products, UUID shoppingListId) {
        return products.stream().map(product -> new ProductSelectionJpa(product.getId(), shoppingListId, 1)).collect(Collectors.toList());
    }

    List<ProductJpa> getProducts(ProductCategoryJpa category, String ... names) {
        return Stream.of(names).map(name -> getProduct(name, category)).collect(Collectors.toList());
    }

    LocalDateTime getDate(Month month) {
        return LocalDateTime.of(LocalDate.of(2021, month, 1), LocalTime.MIN);
    }

    ProductJpa getProduct(String name, ProductCategoryJpa category) {
        var product = new ProductJpa();
        product.setName(name);
        product.setCategory(category);
        return product;
    }

    ProductCategoryJpa getCategory() {
        var productCategory = new ProductCategoryJpa();
        productCategory.setName("Category");
        return productCategory;
    }

    ShoppingListJpa getShoppingList(LocalDateTime createdAt) {
        var shoppingList = new ShoppingListJpa();
        shoppingList.setCreatedAt(createdAt);
        shoppingList.setLocked(false);
        return shoppingList;
    }
}