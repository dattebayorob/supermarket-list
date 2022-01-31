package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product;

import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginatedProductsResponse;
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
    public ResponseEntity<PaginatedProductsResponse> findAll(String name, String category, String shoppingListIdToIgnore, Integer page, Integer size, List<String> sort) {
        var filters = new ProductFilters(name, category, shoppingListIdToIgnore, page, size, sort);
        var pagination = findProductsService.findAll(filters).map(productMapper::toResponse);
        return ResponseEntity.ok(toResponse(pagination));
    }
    private PaginatedProductsResponse toResponse(Pagination<ProductResponse> pagination) {
        return new PaginatedProductsResponse()
                .content(pagination.getContent())
                .page(pagination.getPage())
                .size(pagination.getSize())
                .total((int)pagination.getTotal());
    }
}
