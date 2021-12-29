package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductJpa, UUID>, JpaSpecificationExecutor<ProductJpa> {
    @Query(value = "select p.* from product p join product_selection ps on ps.product_id = p.id where ps.shoppinglist_id = :shoppingListId order by p.name", nativeQuery = true)
    List<ProductJpa> findByShoppingListId(@Param("shoppingListId") UUID shoppingListId);
}
