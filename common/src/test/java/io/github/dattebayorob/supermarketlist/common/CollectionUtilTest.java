package io.github.dattebayorob.supermarketlist.common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionUtilTest {
    @Test
    public void shouldReturnTrueIfCollectionIsEmpty() {
        assertTrue(CollectionUtil.isEmpty(new ArrayList<>()));
    }
    @Test
    public void shouldReturnTrueIfCollectionIsNull() {
        assertTrue(CollectionUtil.isEmpty(null));
    }
    @Test
    public void shouldReturnFalseIfCollectionIsNotEmpty() {
        var strings = Arrays.asList("");
        assertTrue(CollectionUtil.isNotEmpty(strings));
    }
}