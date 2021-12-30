package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category;

import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.SaveProductCategoryService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

import static java.lang.String.format;
import static java.net.URI.create;

@Component
@RequiredArgsConstructor
public class SaveProductCategoryEndpoint {
    private final SaveProductCategoryService saveProductCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ResponseEntity<ProductCategoryResponse> saveCategoy(ProductCategoryRequest category) {
        var domain = productCategoryMapper.toDomain(category);
        var response = productCategoryMapper.toResponse(
                saveProductCategoryService.save(domain)
        );
        URI uri = create(format("/v1/categories/%s", response.getId()));
        return ResponseEntity.created(uri).body(response);
    }
}
