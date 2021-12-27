package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.ProductCategoryEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.ProductEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.ProductsByProductCategoryIdEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class V1ApiController implements V1Api{
    private final ProductCategoryEndpoint productCategoryEndpoint;
    private final ProductEndpoint productEndpoint;
    private final ProductsByProductCategoryIdEndpoint productsByProductCategoryIdEndpoint;

    @Override
    public ResponseEntity<List<ProductCategoryResponse>> categoriesFindCategories(String name) {
        return productCategoryEndpoint.findCategoriesByName(name);
    }

    @Override
    public ResponseEntity<ProductCategoryResponse> categoriesSaveCategory(ProductCategoryRequest category) {
        return productCategoryEndpoint.saveCategoy(category);
    }

    @Override
    public ResponseEntity<ProductCategoryResponse> categoriesUpdateCategory(String id, ProductCategoryRequest category) {
        return productCategoryEndpoint.updateCategory(id, category);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> productsFindAll(String name, String category) {
        return productEndpoint.findAll(name, category);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> categoriesFindProductsByCategoryId(String categoryId) {
        return productsByProductCategoryIdEndpoint.findAllByCategoryId(categoryId);
    }
}
