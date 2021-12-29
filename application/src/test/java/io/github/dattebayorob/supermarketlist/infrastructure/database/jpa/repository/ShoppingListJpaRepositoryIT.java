package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles({ "dev", "in-memory" })
class ShoppingListJpaRepositoryIT {
    @Autowired ShoppingListJpaRepository shoppingListJpaRepository;

    @Test
    void shouldFindShoppingListFromDatePeriod() {
        var january = LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0);
        var february = LocalDateTime.of(2019, Month.FEBRUARY, 1, 0, 0);
        var june = LocalDateTime.of(2019, Month.JUNE, 1, 0,0);
        var september = LocalDateTime.of(2019, Month.SEPTEMBER, 1, 0, 0);
        var december = LocalDateTime.of(2019, Month.DECEMBER, 1,0,0);
        var list1 = getShoppingList(january);
        var list2 = getShoppingList(june);
        var list3 = getShoppingList(december);
        shoppingListJpaRepository.saveAll(Arrays.asList(list1, list2, list3));

        assertThat(shoppingListJpaRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(january, december))
                .hasSize(3)
                .contains(list1, list2, list3);
        assertThat(shoppingListJpaRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(february, september))
                .hasSize(1)
                .contains(list2);
    }

    ShoppingListJpa getShoppingList(LocalDateTime createdAt) {
        var shoppingList = new ShoppingListJpa();
        shoppingList.setLocked(false);
        shoppingList.setCreatedAt(createdAt);
        return shoppingList;
    }
}