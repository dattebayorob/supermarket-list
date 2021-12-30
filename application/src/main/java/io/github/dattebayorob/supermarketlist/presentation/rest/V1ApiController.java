package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category.FindProductCategoriesEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category.SaveProductCategoryEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category.UpdateCategoryEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product.FindProductEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product.ProductsByProductCategoryIdEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct.*;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class V1ApiController implements V1Api{
    private final FindProductCategoriesEndpoint findProductCategoriesEndpoint;
    private final SaveProductCategoryEndpoint saveProductCategoryEndpoint;
    private final UpdateCategoryEndpoint productCategoryEndpoint;
    private final FindProductEndpoint productEndpoint;
    private final ProductsByProductCategoryIdEndpoint productsByProductCategoryIdEndpoint;
    private final DecreaseProductOnListEndpoint decreaseProductOnListEndpoint;
    private final IncreaseProductOnListEndpoint increaseProductOnListEndpoint;
    private final UpdateProductQuantityOnListEndpoint updateProductQuantityOnListEndpoint;
    private final RemoveProductFromListEndpoint removeProductFromListEndpoint;
    private final FindProductsByShoppingListEndpoint findProductsByShoppingListEndpoint;

    @Override
    public ResponseEntity<List<ProductCategoryResponse>> categoriesFindCategories(String name) {
        return findProductCategoriesEndpoint.findCategoriesByName(name);
    }

    @Override
    public ResponseEntity<ProductCategoryResponse> categoriesSaveCategory(ProductCategoryRequest category) {
        return saveProductCategoryEndpoint.saveCategoy(category);
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

    @Override
    public ResponseEntity<Void> shoppingProductsDecreaseProductsInList(String shoppingListId, String productId) {
        return decreaseProductOnListEndpoint.decreaseProductsOnList(shoppingListId, productId);
    }

    @Override
    public ResponseEntity<Void> shoppingProductsIncreaseProductsInList(String shoppingListId, String productId) {
        return increaseProductOnListEndpoint.increaseProductsOnList(shoppingListId, productId);
    }

    @Override
    public ResponseEntity<Void> shoppingProductsPutProductInList(String shoppingListId, String productId, Integer quantity) {
        return updateProductQuantityOnListEndpoint.putProductOnList(shoppingListId, productId, quantity);
    }

    @Override
    public ResponseEntity<Void> shoppingProductsRemoveProductInList(String shoppingListId, String productId) {
        return removeProductFromListEndpoint.removeProductOnList(shoppingListId, productId);
    }

    @Override
    public ResponseEntity<List<ProductSelectionResponse>> shoppingListProductsFindAll(String shoppingListId) {
        return findProductsByShoppingListEndpoint.findByProductListId(shoppingListId);
    }
}
