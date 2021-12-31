package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionId;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.projection.ProductSelectionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductSelectionJpaRepository extends JpaRepository<ProductSelectionJpa, ProductSelectionId> {
    @Query(value = "select \n" +
            "\tcast(p.id as varchar) as productId,\n" +
            "\tp.name as productName,\n" +
            "\tcast(pc.id as varchar) as productCategoryId,\n" +
            "\tpc.name as productCategoryName,\n" +
            "\tcast(ps.shoppinglist_id as varchar) as shoppinglistId,\n" +
            "\tps.quantity as quantity, \n" +
            "\tps.checked as checked \n" +
            "from product p\n" +
            "\tjoin product_selection ps on ps.product_id = p.id\n" +
            "\tjoin product_category pc on p.category_id  = pc.id\n" +
            "where ps.shoppinglist_id = :shoppingListId order by p.name", nativeQuery = true)
    List<ProductSelectionProjection> findByShoppingListId(@Param("shoppingListId") UUID shoppingListId);
}
