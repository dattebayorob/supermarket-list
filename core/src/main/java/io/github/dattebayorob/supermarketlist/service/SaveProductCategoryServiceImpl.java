package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ErrorCode;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import io.github.dattebayorob.supermarketlist.port.out.SaveProductCategoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveProductCategoryServiceImpl implements SaveProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        if ( productCategoryRepository.existsByName(productCategory.getName()) ) {
            throw new BusinessException("Nome já utilizado por outra categoria de produto!").code(ErrorCode.DUPLICATED_VALUES_ERROR);
        }
        return productCategoryRepository.save(productCategory);
    }
}
