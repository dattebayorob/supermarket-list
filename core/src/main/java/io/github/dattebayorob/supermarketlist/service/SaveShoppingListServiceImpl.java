package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.SaveShoppingListService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SaveShoppingListServiceImpl implements SaveShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ProductSelectionRepository productSelectionRepository;
    @Override
    public ShoppingList save(List<ProductSelection> selections) {
        final var shoppingList = shoppingListRepository.save(buildShoppingList());
        if (CollectionUtil.isNotEmpty(selections)) {
            saveProductSelections(selections, shoppingList);
            shoppingList.setEmpty(false);
        }
        return shoppingList;
    }

    private ShoppingList buildShoppingList() {
        var shoppingList = new ShoppingList();
        shoppingList.setLocked(false);
        shoppingList.setCreatedAt(LocalDateTime.now());
        shoppingList.setEmpty(true);
        return shoppingList;
    }

    private void saveProductSelections(List<ProductSelection> selections, ShoppingList shoppingList) {
        productSelectionRepository.saveAll(selections.stream().map(selection -> {
            selection.setShoppingList(shoppingList);
            return selection;
        }).collect(Collectors.toList()));
    }
}
