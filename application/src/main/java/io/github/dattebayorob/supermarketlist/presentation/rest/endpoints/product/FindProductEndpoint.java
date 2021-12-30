package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product;

import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindProductEndpoint {
    private final FindProductsService findProductsService;
    private final ProductMapper productMapper;
    public ResponseEntity<List<ProductResponse>> findAll(String name, String category) {
        var products = findProductsService.findAll(new ProductFilters(name, category));
        return ResponseEntity.ok(productMapper.toResponse(products));
    }
}
