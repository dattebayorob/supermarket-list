package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.ProductCategoryEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class V1ApiController implements V1Api{
    private final ProductCategoryEndpoint productCategoryEndpoint;

    @Override
    public ResponseEntity<List<ProductCategoryResponse>> categoriesFindCategories(String name) {
        return productCategoryEndpoint.findCategoriesByName(name);
    }

}
