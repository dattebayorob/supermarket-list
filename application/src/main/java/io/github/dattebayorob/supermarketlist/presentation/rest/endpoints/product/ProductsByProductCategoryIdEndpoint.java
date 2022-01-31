package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product;

import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginatedProductsResponse;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class ProductsByProductCategoryIdEndpoint {
    private final FindProductEndpoint productEndpoint;

    public ResponseEntity<List<ProductResponse>> findAllByCategoryId(String categoryId) {
        return ofNullable(productEndpoint.findAll(null, categoryId, null, 0, 0, null))
                .map(ResponseEntity::getBody)
                .map(PaginatedProductsResponse::getContent)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok(Collections.emptyList()));
    }
}
