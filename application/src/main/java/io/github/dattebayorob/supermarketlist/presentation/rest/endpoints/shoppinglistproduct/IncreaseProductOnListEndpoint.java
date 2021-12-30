package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.IncreaseProductQuantityInProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IncreaseProductOnListEndpoint {
    private final IncreaseProductQuantityInProductListService increaseProductQuantityInProductListService;

    public ResponseEntity<Void> increaseProductsOnList(String shoppingListId, String productId) {
        increaseProductQuantityInProductListService.increaseProductQuantityInProductList(
                UUID.fromString(productId), UUID.fromString(shoppingListId)
        );
        return ResponseEntity.accepted().build();
    }
}
