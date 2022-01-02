package io.github.dattebayorob.supermarketlist.config;

import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.*;
import io.github.dattebayorob.supermarketlist.service.*;
import io.github.dattebayorob.supermarketlist.service.validation.ValidateShoppingListAndProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Beans {
    private final ProductRepository productRepository;
    private final ProductSelectionRepository productSelectionRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Bean
    public AddProductSelectionToProductListService addProductSelectionToProductListService() {
        return new AddProductSelectionToProductListServiceImpl(productRepository, shoppingListRepository, productSelectionRepository);
    }

    @Bean
    public FindShoppingListsService findProductListsService() {
        return new FindShoppingListsServiceImpl(shoppingListRepository);
    }
    @Bean
    public FindProductsService findProductsService() {
        return new FindProductsServiceImpl(productRepository);
    }
    @Bean
    public ProductSelectionQuantityServiceImpl productSelectionQuantityService() {
        return new ProductSelectionQuantityServiceImpl(productSelectionRepository);
    }
    @Bean
    public FindProductCategoriesByNameLikeService findProductCategoriesByNameLikeService() {
        return new FindProductCategoriesByNameLikeServiceImpl(productCategoryRepository);
    }
    @Bean
    public SaveProductCategoryService saveProductCategoryService() {
        return new SaveProductCategoryServiceImpl(productCategoryRepository);
    }
    @Bean
    public UpdateProductCategoryService updateProductCategoryService() {
        return new UpdateProducCategoryServiceImpl(productCategoryRepository);
    }
    @Bean
    public RemoveProductFromShoppingListService removeProductFromShoppingListService() {
        return new RemoveProductSelectionToShoppingListServiceImpl(productSelectionRepository, shoppingListRepository, productRepository);
    }
    @Bean
    public FindProductsByShoppingListIdService findProductsByShoppingListIdService() {
        return new FindProductsByShoppingListIdServiceImpl(shoppingListRepository,productSelectionRepository);
    }
    @Bean
    public ValidateShoppingListAndProduct validateShoppingListAndProduct() {
        return new ValidateShoppingListAndProduct(shoppingListRepository, productRepository);
    }
    @Bean
    public CheckOrUncheckProductInListServiceImpl checkOrUncheckProductInListService(
            final ValidateShoppingListAndProduct validateShoppingListAndProduct, final ProductSelectionRepository productSelectionRepository
    ) {
        return new CheckOrUncheckProductInListServiceImpl(validateShoppingListAndProduct, productSelectionRepository);
    }
    @Bean
    public SaveShoppingListService saveShoppingListService(
            final ShoppingListRepository shoppingListRepository, ProductSelectionRepository productSelectionRepository
    ) {
        return new SaveShoppingListServiceImpl(shoppingListRepository, productSelectionRepository);
    }
    @Bean
    public CloneShoppingListService cloneShoppingListService(
            final ShoppingListRepository shoppingListRepository, final ProductSelectionRepository productSelectionRepository
    ) {
        return new CloneShoppingListServiceImpl(shoppingListRepository, productSelectionRepository);
    }
}
