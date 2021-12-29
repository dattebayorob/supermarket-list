package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.port.out.RemoveProductFromShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RemoveProductFromListEndpoint {
    private final RemoveProductFromShoppingListService removeProductFromShoppingListService;

    public ResponseEntity<Void> removeProductOnList(String shoppingListId, String productId) {
        removeProductFromShoppingListService.removeProductFromShoppingList(
                UUID.fromString(productId), UUID.fromString(shoppingListId)
        );
        return ResponseEntity.accepted().build();
    }
}
