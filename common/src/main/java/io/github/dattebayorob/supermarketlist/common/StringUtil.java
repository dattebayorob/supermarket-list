package io.github.dattebayorob.supermarketlist.common;

public class StringUtil {
    private StringUtil() {}
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
