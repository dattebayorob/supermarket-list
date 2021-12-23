package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class FindProductsServiceImpl implements FindProductsService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return Collections.emptyList();
    }
}
