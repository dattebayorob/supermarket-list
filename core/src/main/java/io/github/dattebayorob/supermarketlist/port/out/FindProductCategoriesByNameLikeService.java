package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;

import java.util.List;

public interface FindProductCategoriesByNameLikeService {
    List<ProductCategory> findByNameLike(String name);
}
