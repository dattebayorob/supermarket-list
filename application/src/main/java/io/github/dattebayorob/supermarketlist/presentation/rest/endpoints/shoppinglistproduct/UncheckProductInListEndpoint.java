package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglistproduct;

import io.github.dattebayorob.supermarketlist.port.out.UncheckProductInListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UncheckProductInListEndpoint {
    private final UncheckProductInListService uncheckProductInListService;
    public ResponseEntity<Void> unCheck(String shoppingListId, String productId) {
        uncheckProductInListService.uncheckProductInList(
                UUID.fromString(shoppingListId), UUID.fromString(productId)
        );
        return ResponseEntity.accepted().build();
    }
}
