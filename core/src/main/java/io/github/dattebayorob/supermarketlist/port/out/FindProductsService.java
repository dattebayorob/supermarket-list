package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.Product;

import java.util.List;

public interface FindProductsService {
    List<Product> findAll();
}
