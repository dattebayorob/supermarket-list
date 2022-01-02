package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist;

import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.out.CloneShoppingListService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.UUID;
import java.util.function.Function;

import static java.lang.String.format;
import static java.net.URI.create;
import static org.springframework.http.ResponseEntity.notFound;

@Component
@RequiredArgsConstructor
public class CloneShoppingListEndpoint {
    private final CloneShoppingListService cloneShoppingListService;
    private final ShoppingListMapper shoppingListMapper;

    public ResponseEntity<ShoppingListResponse> clone(String shoppingListId) {
        return cloneShoppingListService.clone(UUID.fromString(shoppingListId))
                .map(shoppingListMapper::toResponse)
                .map(mapResponseToResponseEntityCreated())
                .orElseGet(notFound()::build);
    }

    private Function<ShoppingListResponse, ResponseEntity<ShoppingListResponse>> mapResponseToResponseEntityCreated() {
        return response -> {
            URI uri = create(format("/v1/lists/%s", response.getId()));
            return ResponseEntity.created(uri).body(response);
        };
    }
}
