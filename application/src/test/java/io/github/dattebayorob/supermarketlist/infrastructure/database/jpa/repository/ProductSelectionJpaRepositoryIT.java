package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.*;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductSelectionMapper;
import org.assertj.core.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles({ "dev", "in-memory" })
class ProductSelectionJpaRepositoryIT {
    @Autowired ProductJpaRepository productJpaRepository;
    @Autowired ProductCategoryJpaRepository productCategoryJpaRepository;
    @Autowired ShoppingListJpaRepository shoppingListJpaRepository;
    @Autowired ProductSelectionJpaRepository productSelectionJpaRepository;

    @Test
    void shouldFindByProductIdAndShoppingListId() {
        var category = getCategory();
        productCategoryJpaRepository.save(category);
        var product = getProduct();
        product.setCategory(category);
        productJpaRepository.save(product);
        var shoppingList = getShoppingList();
        shoppingListJpaRepository.save(shoppingList);
        var id = new ProductSelectionId(product.getId(), shoppingList.getId());
        var selection = new ProductSelectionJpa();
        selection.setId(id);
        selection.setQuantity(3);
        productSelectionJpaRepository.save(selection);
        Assertions.assertThat(productSelectionJpaRepository.findById(id))
                .isNotEmpty()
                .get()
                .hasFieldOrPropertyWithValue("quantity", 3);
    }

    @Test
    void shouldRemoveById() {
        var category = getCategory();
        productCategoryJpaRepository.save(category);
        var product = getProduct();
        product.setCategory(category);
        productJpaRepository.save(product);
        var shoppingList = getShoppingList();
        shoppingListJpaRepository.save(shoppingList);
        var id = new ProductSelectionId(product.getId(), shoppingList.getId());
        var selection = new ProductSelectionJpa();
        selection.setId(id);
        selection.setQuantity(3);
        productSelectionJpaRepository.save(selection);

        assertTrue(productSelectionJpaRepository.existsById(id));

        productSelectionJpaRepository.deleteById(id);

        assertFalse(productSelectionJpaRepository.existsById(id));

    }

    @Test
    void shouldFindProductsByShoppingListId() {
        var category = productCategoryJpaRepository.save(getCategory());
        var products = productJpaRepository.saveAll(getProducts(category, "Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6"));
        var products2 = productJpaRepository.saveAll(getProducts(category, "Product 7", "Product 8", "Product 9", "Product 10"));
        var shoppingList = shoppingListJpaRepository.save(getShoppingList(getDate(Month.JANUARY)));
        var shoppingList2 = shoppingListJpaRepository.save(getShoppingList(getDate(Month.FEBRUARY)));

        productSelectionJpaRepository.saveAll(mapSelections(products, shoppingList.getId()));
        productSelectionJpaRepository.saveAll(mapSelections(products2, shoppingList2.getId()));

        assertThat(productSelectionJpaRepository.findByShoppingListId(shoppingList.getId())).hasSize(6);
        assertThat(productSelectionJpaRepository.findByShoppingListId(shoppingList2.getId())).hasSize(4);

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

    ShoppingListJpa getShoppingList(LocalDateTime createdAt) {
        var shoppingList = new ShoppingListJpa();
        shoppingList.setCreatedAt(createdAt);
        shoppingList.setLocked(false);
        return shoppingList;
    }

    ProductJpa getProduct() {
        var product = new ProductJpa();
        product.setName("Product");
        return product;
    }

    ProductCategoryJpa getCategory() {
        var productCategory = new ProductCategoryJpa();
        productCategory.setName("Category");
        return productCategory;
    }

    ShoppingListJpa getShoppingList() {
        var shoppingList = new ShoppingListJpa();
        shoppingList.setCreatedAt(LocalDateTime.now());
        shoppingList.setLocked(false);
        return shoppingList;
    }
}