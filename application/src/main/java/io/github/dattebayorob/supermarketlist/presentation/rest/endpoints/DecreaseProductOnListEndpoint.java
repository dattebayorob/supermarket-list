package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.port.out.DecreaseProductQuantityInProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DecreaseProductOnListEndpoint {
    private final DecreaseProductQuantityInProductListService decreaseProductQuantityInProductListService;
    public ResponseEntity<Void> decreaseProductsOnList(String shoppingListId, String productId) {
        decreaseProductQuantityInProductListService.decreaseProductQuantityInProductList(
                UUID.fromString(productId), UUID.fromString(shoppingListId)
        );
        return ResponseEntity.accepted().build();
    }
}
