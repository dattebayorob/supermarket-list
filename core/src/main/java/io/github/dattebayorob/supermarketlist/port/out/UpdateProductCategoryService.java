package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;

import java.util.Optional;

public interface UpdateProductCategoryService {
    Optional<ProductCategory> update(ProductCategory productCategory);
}
