package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;

public interface SaveProductCategoryService {
    ProductCategory save(ProductCategory productCategory);
}
