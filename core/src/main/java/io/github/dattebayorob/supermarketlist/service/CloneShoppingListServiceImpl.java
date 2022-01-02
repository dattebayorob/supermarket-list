package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.CloneShoppingListService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class CloneShoppingListServiceImpl implements CloneShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ProductSelectionRepository productSelectionRepository;

    @Override
    public Optional<ShoppingList> clone(UUID shoppingListId) {
        return shoppingListRepository.findById(shoppingListId)
                .map(createNewShoppingList())
                .map(saveProductSelections(shoppingListId));
    }
    private Function<ShoppingList, ShoppingList> createNewShoppingList() {
        return shoppingList -> {
            var cloned = new ShoppingList();
            cloned.setCreatedAt(LocalDateTime.now());
            cloned.setEmpty(true);
            cloned.setLocked(false);
            return shoppingListRepository.save(cloned);
        };
    }
    private Function<ShoppingList, ShoppingList> saveProductSelections(UUID shoppingListId) {
        return shoppingList -> {
            var selections = productSelectionRepository.findByShoppingListId(shoppingListId);
            if (CollectionUtil.isNotEmpty(selections)) {
                shoppingList.setEmpty(false);
                cloneProductSelections(shoppingList, selections);
            }
            return shoppingList;
        };
    }

    private void cloneProductSelections(ShoppingList shoppingList, List<ProductSelection> selections) {
        var clonedSelections = selections.stream().map(
            selection -> {
                selection.setShoppingList(shoppingList);
                return selection;
            }).collect(toList());
        productSelectionRepository.saveAll(clonedSelections);
    }
}
