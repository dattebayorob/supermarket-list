package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository {
    List<ProductCategory> findByNameLike(String name);
}
