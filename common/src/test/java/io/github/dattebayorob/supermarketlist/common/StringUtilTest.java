package io.github.dattebayorob.supermarketlist.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {
    @Test
    public void shouldValidateIfStringIsEmpty() {
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty(null));
    }
    @Test
    public void shouldValidateIfStringIsNotEmpty() {
        assertTrue(StringUtil.isNotEmpty("Not Empty"));
        assertFalse(StringUtil.isNotEmpty(""));
    }
}