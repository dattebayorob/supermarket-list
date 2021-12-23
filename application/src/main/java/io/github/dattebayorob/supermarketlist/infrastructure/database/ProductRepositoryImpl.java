package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;
    @Override
    public List<Product> findAll() {
        return productMapper.toDomain(
            productJpaRepository.findAll()
        );
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }
}
