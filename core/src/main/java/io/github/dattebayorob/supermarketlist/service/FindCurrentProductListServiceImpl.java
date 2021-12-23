package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindCurrentProductListService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class FindCurrentProductListServiceImpl implements FindCurrentProductListService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductSelection> findCurrentProductList() {
        return Collections.emptyList();
    }
}
