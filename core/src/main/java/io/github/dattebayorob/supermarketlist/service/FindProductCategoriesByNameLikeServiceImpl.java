package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.common.StringUtil;
import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindProductCategoriesByNameLikeService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindProductCategoriesByNameLikeServiceImpl implements FindProductCategoriesByNameLikeService {
    private final ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByNameLike(String name) {
        return productCategoryRepository.findByNameLike(
                name == null ? "" : name
        );
    }
}
