package io.github.dattebayorob.supermarketlist.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilTest {
    @Test
    void shouldValidateIfStringIsEmpty() {
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty(null));
    }
    @Test
    void shouldValidateIfStringIsNotEmpty() {
        assertTrue(StringUtil.isNotEmpty("Not Empty"));
        assertFalse(StringUtil.isNotEmpty(""));
    }
}