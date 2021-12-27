package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepository {
    boolean existsByName(String name);
    boolean existsByNameAndIdNotIn(String name, UUID id);
    Optional<ProductCategory> findById(UUID id);
    List<ProductCategory> findByNameLike(String name);
    ProductCategory save(ProductCategory productCategory);
}
