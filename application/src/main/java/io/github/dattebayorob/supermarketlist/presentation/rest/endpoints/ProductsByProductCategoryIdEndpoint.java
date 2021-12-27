package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsByProductCategoryIdEndpoint {
    private final ProductEndpoint productEndpoint;

    public ResponseEntity<List<ProductResponse>> findAllByCategoryId(String categoryId) {
        return productEndpoint.findAll(null, categoryId);
    }
}
