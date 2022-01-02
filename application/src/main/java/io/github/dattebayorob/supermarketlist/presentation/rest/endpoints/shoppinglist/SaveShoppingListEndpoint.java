package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.shoppinglist;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductSelectionMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.out.SaveShoppingListService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static java.net.URI.create;

@Component
@RequiredArgsConstructor
public class SaveShoppingListEndpoint {
    private final SaveShoppingListService saveShoppingListService;
    private final ShoppingListMapper shoppingListMapper;
    private final ProductSelectionMapper productSelectionMapper;

    public ResponseEntity<ShoppingListResponse> save(ShoppingListRequest shoppingListRequest) {
        var shoppingList = saveShoppingListService.save(selectionsRequestToSelection(
            shoppingListRequest.getProducts()
        ));
        URI uri = create(format("/v1/lists/%s", shoppingList.getId()));
        var response = shoppingListMapper.toResponse(shoppingList);
        return ResponseEntity.created(uri).body(response);
    }

    private List<ProductSelection> selectionsRequestToSelection(List<ProductSelectionRequest> selections) {
        return CollectionUtil.map(selections, productSelectionMapper::toDomain);
    }
}
