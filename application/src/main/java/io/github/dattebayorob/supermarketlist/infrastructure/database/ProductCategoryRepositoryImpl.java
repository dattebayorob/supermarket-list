package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductCategoryJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductCategoryMapper;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    private final ProductCategoryJpaRepository productCategoryJpaRepository;
    private final ProductCategoryMapper productCategoryMapper;

    @Override
    public boolean existsByName(String name) {
        return productCategoryJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNotIn(String name, UUID id) {
        return productCategoryJpaRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public List<ProductCategory> findByNameLike(String name) {
        return productCategoryMapper.toDomain(
            productCategoryJpaRepository.findByNameContainingIgnoreCase(name)
        );
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        var entity = productCategoryJpaRepository.save(
            productCategoryMapper.toEntity(productCategory)
        );
        return productCategoryMapper.toDomain(entity);
    }

    @Override
    public Optional<ProductCategory> findById(UUID id) {
        return productCategoryJpaRepository.findById(id)
                .map(productCategoryMapper::toDomain);
    }
}
