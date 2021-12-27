package io.github.dattebayorob.supermarketlist.config;

import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.*;
import io.github.dattebayorob.supermarketlist.service.*;
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
    public FindCurrentProductListService findCurrentProductListService() {
        return new FindCurrentProductListServiceImpl(productRepository);
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
}
