package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints;

import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductSelectionMapper;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsByShoppingListIdService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindProductsByShoppingListEndpoint {
    private final FindProductsByShoppingListIdService findProductsByShoppingListIdService;
    private final ProductSelectionMapper productSelectionMapper;

    public ResponseEntity<List<ProductSelectionResponse>> findByProductListId(String shoppingListId) {
        var selections = findProductsByShoppingListIdService.findProductsByShoppingListId(UUID.fromString(shoppingListId));
        return ResponseEntity.ok(productSelectionMapper.toResponse(selections));
    }
}
