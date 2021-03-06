package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindProductsServiceImpl implements FindProductsService {
    private final ProductRepository productRepository;
    @Override
    public Pagination<Product> findAll(ProductFilters filters) {
        return productRepository.findAll(filters);
    }
}
