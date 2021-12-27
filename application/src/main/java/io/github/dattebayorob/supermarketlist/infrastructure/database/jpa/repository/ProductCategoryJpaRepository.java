package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategoryJpa, UUID> {
    List<ProductCategoryJpa> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, UUID id);
}
