package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ErrorCode;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import io.github.dattebayorob.supermarketlist.port.out.UpdateProductCategoryService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class UpdateProducCategoryServiceImpl implements UpdateProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public Optional<ProductCategory> update(ProductCategory productCategory) {
        if (productCategoryRepository.existsByNameAndIdNotIn(productCategory.getName(), productCategory.getId())) {
            throw new BusinessException("Nome já utilizado por outra categoria de produto!").code(ErrorCode.DUPLICATED_VALUES_ERROR);
        }
        return productCategoryRepository.findById(productCategory.getId())
                .map(updateSavedCategoryName(productCategory))
                .map(productCategoryRepository::save);
    }

    private Function<ProductCategory, ProductCategory> updateSavedCategoryName(ProductCategory productCategory) {
        return saved -> {
            saved.setName(productCategory.getName());
            return saved;
        };
    }
}
