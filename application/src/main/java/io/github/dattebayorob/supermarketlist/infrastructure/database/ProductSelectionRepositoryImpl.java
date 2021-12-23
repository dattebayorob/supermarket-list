package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductSelectionRepositoryImpl implements ProductSelectionRepository {
    @Override
    public ProductSelection save(ProductSelection productSelection) {
        return null;
    }

    @Override
    public void updateProductSelectionQuantity(UUID productSelectionId, int quantity) {

    }

    @Override
    public Optional<Integer> getProductSelectionQuantity(UUID productSelectionQuantity) {
        return Optional.empty();
    }
}
