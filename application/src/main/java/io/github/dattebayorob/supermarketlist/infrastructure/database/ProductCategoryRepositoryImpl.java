package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductCategoryJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    private final ProductCategoryJpaRepository productCategoryJpaRepository;
    private final ProductCategoryMapper productCategoryMapper;
    @Override
    public List<ProductCategory> findByNameLike(String name) {
        return productCategoryMapper.toDomain(
                productCategoryJpaRepository.findByNameContainingIgnoreCase(name)
        );
    }
}
