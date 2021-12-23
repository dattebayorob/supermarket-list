package io.github.dattebayorob.supermarketlist.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().create();
    private JsonUtil(){}
    public static <T>T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
    public static <T>String serialize(T obj) {
        return gson.toJson(obj);
    }
}
