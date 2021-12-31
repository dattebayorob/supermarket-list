package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.CheckProductInListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckProductInListEndpoint {
    private final CheckProductInListService checkProductInListService;
    public ResponseEntity<Void> check(String shoppingListId, String productId) {
        checkProductInListService.checkProductInList(
            UUID.fromString(shoppingListId), UUID.fromString(productId)
        );
        return ResponseEntity.accepted().build();
    }
}
