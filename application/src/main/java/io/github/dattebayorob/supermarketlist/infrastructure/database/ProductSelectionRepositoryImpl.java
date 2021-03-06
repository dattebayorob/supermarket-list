package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.config.security.SessionWrapper;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionId;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductSelectionJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductSelectionMapper;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductSelectionRepositoryImpl implements ProductSelectionRepository {
    private final ProductSelectionJpaRepository productSelectionJpaRepository;
    private final ProductSelectionMapper productSelectionMapper;
    @Override
    public ProductSelection save(ProductSelection productSelection) {
        var entity = productSelectionMapper.toEntity(productSelection);
        entity.setUserId(SessionWrapper.getSession().getId());

        return productSelectionMapper.toDomain(productSelectionJpaRepository.save(entity));
    }

    @Override
    public List<ProductSelection> saveAll(List<ProductSelection> productSelections) {
        var entities = productSelectionMapper.toEntity(productSelections);
        entities = CollectionUtil.map(entities, entity -> {
            entity.setUserId(SessionWrapper.getSession().getId());
            return entity;
        });
        return productSelectionMapper.toDomain(productSelectionJpaRepository.saveAll(
            entities
        ));
    }

    @Override
    public void removeByProductIdAndShoppingListId(UUID productId, UUID shoppingListId) {
        var id = new ProductSelectionId(productId, shoppingListId);
        if ( productSelectionJpaRepository.existsById(id) ) {
            productSelectionJpaRepository.deleteById(id);
        }
    }

    @Override
    public void updateProductSelectionQuantity(UUID productId, UUID shoppingListId, int quantity) {
        var selection = productSelectionJpaRepository.findById(new ProductSelectionId(productId, shoppingListId))
                .orElseGet(() -> new ProductSelectionJpa(productId, shoppingListId));
        if ( selection.getUserId() == null ) {
            selection.setUserId(SessionWrapper.getSession().getId());
        }
        selection.setQuantity(quantity);
        productSelectionJpaRepository.save(selection);
    }

    @Override
    public Optional<Integer> getProductSelectionQuantity(UUID productId, UUID shoppingListId) {
        return productSelectionJpaRepository.findById(new ProductSelectionId(productId, shoppingListId))
                .map(selection -> selection.getQuantity());
    }

    @Override
    public List<ProductSelection> findByShoppingListId(UUID shoppingListId) {
        return CollectionUtil.map(productSelectionJpaRepository.findByShoppingListId(shoppingListId), productSelectionMapper::toDomain);
    }

    @Override
    public Optional<ProductSelection> findByShoppingListIdAndProductId(UUID shoppingListId, UUID productId) {
        return productSelectionJpaRepository.findById(new ProductSelectionId(productId, shoppingListId)).map(productSelectionMapper::toDomain);
    }
}
