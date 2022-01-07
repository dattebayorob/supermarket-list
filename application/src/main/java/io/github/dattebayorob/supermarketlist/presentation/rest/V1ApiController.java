package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category.FindProductCategoriesEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category.SaveProductCategoryEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.category.UpdateCategoryEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product.FindProductEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.product.ProductsByProductCategoryIdEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.security.LoginEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.security.LogoutEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.security.RefreshTokenEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist.CloneShoppingListEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist.SaveShoppingListEndpoint;
import io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct.*;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class V1ApiController implements V1Api{
    private final LoginEndpoint loginEndpoint;
    private final LogoutEndpoint logoutEndpoint;
    private final RefreshTokenEndpoint refreshTokenEndpoint;
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
    private final CheckProductInListEndpoint checkProductInListEndpoint;
    private final UncheckProductInListEndpoint uncheckProductInListEndpoint;
    private final SaveShoppingListEndpoint saveShoppingListEndpoint;
    private final CloneShoppingListEndpoint cloneShoppingListEndpoint;

    @Override
    public ResponseEntity<JwtResponse> login(JwtRequest jwtRequest) {
        return loginEndpoint.login(jwtRequest);
    }

    @Override
    public ResponseEntity<Void> logout(String authorization) {
        return logoutEndpoint.logout(authorization);
    }

    @Override
    public ResponseEntity<JwtResponse> refreshToken(String refreshToken) {
        return refreshTokenEndpoint.refreshToken(refreshToken);
    }

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

    @Override
    public ResponseEntity<Void> shoppingProductsCheck(String shoppingListId, String productId) {
        return checkProductInListEndpoint.check(shoppingListId, productId);
    }

    @Override
    public ResponseEntity<Void> shoppingProductsUncheck(String shoppingListId, String productId) {
        return uncheckProductInListEndpoint.unCheck(shoppingListId, productId);
    }

    @Override
    public ResponseEntity<ShoppingListResponse> shoppingListSave(ShoppingListRequest shoppingList) {
        return saveShoppingListEndpoint.save(shoppingList);
    }

    @Override
    public ResponseEntity<ShoppingListResponse> shoppingListClone(String shoppingListId) {
        return cloneShoppingListEndpoint.clone(shoppingListId);
    }
}
