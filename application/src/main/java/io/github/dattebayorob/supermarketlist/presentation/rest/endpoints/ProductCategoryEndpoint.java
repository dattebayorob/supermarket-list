package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductCategoriesByNameLikeService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductCategoryEndpoint {
    private final FindProductCategoriesByNameLikeService findProductCategoriesByNameLikeService;
    private final ProductCategoryMapper productCategoryMapper;

    public ResponseEntity<List<ProductCategoryResponse>> findCategoriesByName(String name) {
        List<ProductCategory> categories = findProductCategoriesByNameLikeService.findByNameLike(name);
        return ResponseEntity.ok(productCategoryMapper.toResponse(categories));
    }
}
