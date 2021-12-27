package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductCategoriesByNameLikeService;
import io.github.dattebayorob.supermarketlist.port.out.SaveProductCategoryService;
import io.github.dattebayorob.supermarketlist.port.out.UpdateProductCategoryService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static java.net.URI.create;

@Component
@RequiredArgsConstructor
public class ProductCategoryEndpoint {
    private final FindProductCategoriesByNameLikeService findProductCategoriesByNameLikeService;
    private final SaveProductCategoryService saveProductCategoryService;
    private final UpdateProductCategoryService updateProductCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ResponseEntity<List<ProductCategoryResponse>> findCategoriesByName(String name) {
        List<ProductCategory> categories = findProductCategoriesByNameLikeService.findByNameLike(name);
        return ResponseEntity.ok(productCategoryMapper.toResponse(categories));
    }

    public ResponseEntity<ProductCategoryResponse> saveCategoy(ProductCategoryRequest category) {
        var domain = productCategoryMapper.toDomain(category);
        var response = productCategoryMapper.toResponse(
            saveProductCategoryService.save(domain)
        );
        URI uri = create(format("/v1/categories/%s", response.getId()));
        return ResponseEntity.created(uri).body(response);
    }

    public ResponseEntity<ProductCategoryResponse> updateCategory(String id, ProductCategoryRequest category) {
        var domain = productCategoryMapper.toDomain(category);
        domain.setId(UUID.fromString(id));
        return updateProductCategoryService.update(domain)
                .map(productCategoryMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
