package io.github.dattebayorob.supermarketlist.config;

import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
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

    @Bean
    public FindShoppingListsService findProductListsService(final ShoppingListRepository shoppingListRepository, final ProductRepository productRepository) {
        return new FindShoppingListsServiceImpl(shoppingListRepository, productRepository);
    }
    @Bean
    public FindProductsService findProductsService(final ProductRepository productRepository) {
        return new FindProductsServiceImpl(productRepository);
    }
    @Bean
    public ProductSelectionQuantityServiceImpl productSelectionQuantityService(
            final ValidateShoppingListAndProduct validateShoppingListAndProduct,
            final ProductSelectionRepository productSelectionRepository
    ) {
        return new ProductSelectionQuantityServiceImpl(productSelectionRepository, validateShoppingListAndProduct);
    }
    @Bean
    public FindProductCategoriesByNameLikeService findProductCategoriesByNameLikeService(final ProductCategoryRepository productCategoryRepository) {
        return new FindProductCategoriesByNameLikeServiceImpl(productCategoryRepository);
    }
    @Bean
    public SaveProductCategoryService saveProductCategoryService(final ProductCategoryRepository productCategoryRepository) {
        return new SaveProductCategoryServiceImpl(productCategoryRepository);
    }
    @Bean
    public UpdateProductCategoryService updateProductCategoryService(final ProductCategoryRepository productCategoryRepository) {
        return new UpdateProducCategoryServiceImpl(productCategoryRepository);
    }
    @Bean
    public RemoveProductFromShoppingListService removeProductFromShoppingListService(
            final ProductSelectionRepository productSelectionRepository,
            final ShoppingListRepository shoppingListRepository,
            final ProductRepository productRepository
    ) {
        return new RemoveProductSelectionToShoppingListServiceImpl(productSelectionRepository, shoppingListRepository, productRepository);
    }
    @Bean
    public FindProductsByShoppingListIdService findProductsByShoppingListIdService(
            final ShoppingListRepository shoppingListRepository,
            final ProductSelectionRepository productSelectionRepository
    ) {
        return new FindProductsByShoppingListIdServiceImpl(shoppingListRepository,productSelectionRepository);
    }
    @Bean
    public ValidateShoppingListAndProduct validateShoppingListAndProduct(
            final ShoppingListRepository shoppingListRepository,
            final ProductRepository productRepository
    ) {
        return new ValidateShoppingListAndProduct(shoppingListRepository, productRepository);
    }
    @Bean
    public CheckOrUncheckProductInListServiceImpl checkOrUncheckProductInListService(
            final ValidateShoppingListAndProduct validateShoppingListAndProduct,
            final ProductSelectionRepository productSelectionRepository
    ) {
        return new CheckOrUncheckProductInListServiceImpl(validateShoppingListAndProduct, productSelectionRepository);
    }
    @Bean
    public SaveShoppingListService saveShoppingListService(
            final ShoppingListRepository shoppingListRepository,
            final ProductSelectionRepository productSelectionRepository
    ) {
        return new SaveShoppingListServiceImpl(shoppingListRepository, productSelectionRepository);
    }
    @Bean
    public CloneShoppingListService cloneShoppingListService(
            final ShoppingListRepository shoppingListRepository,
            final ProductSelectionRepository productSelectionRepository
    ) {
        return new CloneShoppingListServiceImpl(shoppingListRepository, productSelectionRepository);
    }
    @Bean
    public PaginationMapper paginationMapper() {
        return new PaginationMapper();
    }
}
