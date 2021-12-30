package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category;

import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.UpdateProductCategoryService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateCategoryEndpoint {
    private final UpdateProductCategoryService updateProductCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ResponseEntity<ProductCategoryResponse> updateCategory(String id, ProductCategoryRequest category) {
        var domain = productCategoryMapper.toDomain(category);
        domain.setId(UUID.fromString(id));
        return updateProductCategoryService.update(domain)
                .map(productCategoryMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
