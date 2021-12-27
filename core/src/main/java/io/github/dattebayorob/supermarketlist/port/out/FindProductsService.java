package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;

import java.util.List;

public interface FindProductsService {
    List<Product> findAll(ProductFilters filters);
}
