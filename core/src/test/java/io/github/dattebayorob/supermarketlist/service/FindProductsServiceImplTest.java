package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FindProductsServiceImplTest {
    @Mock ProductRepository productRepository;
    @Mock ProductFilters filters;

    FindProductsServiceImpl findProductsService;

    @BeforeEach
    void beforeEach() {
        findProductsService = new FindProductsServiceImpl(productRepository);
    }

    @Test
    void shouldFindAll() {
        List<Product> products = new ArrayList<>();
        Mockito.when(productRepository.findAll(filters)).thenReturn(products);
        Assertions.assertEquals(products, findProductsService.findAll(filters));
        Mockito.verify(productRepository).findAll(filters);
    }
}
