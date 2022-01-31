package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;

public interface FindProductsService {
    Pagination<Product> findAll(ProductFilters filters);
}
