package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.port.out.UpdateProductQuantityInProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateProductQuantityOnListEndpoint {
    private final UpdateProductQuantityInProductListService updateProductQuantityInProductListService;

    public ResponseEntity<Void> putProductOnList(String shoppingListId, String productId, Integer quantity) {
        updateProductQuantityInProductListService.updateProductQuantityInProductListService(
                UUID.fromString(productId), UUID.fromString(shoppingListId), quantity
        );
        return ResponseEntity.accepted().build();
    }
}
