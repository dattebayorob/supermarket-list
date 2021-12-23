package io.github.dattebayorob.supermarketlist.port.out;

import java.util.Optional;
import java.util.UUID;

public interface GetProductQuantityInProductListService {
    Optional<Integer> getProductQuantityInProductList(UUID productSelectionId);
}
