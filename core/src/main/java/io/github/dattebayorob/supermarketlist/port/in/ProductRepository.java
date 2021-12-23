package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    List<Product> findAll();
    boolean existsById(UUID id);
}
