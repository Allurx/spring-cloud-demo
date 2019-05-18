package com.zyc.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

/**
 * Gson工具类
 *
 * @author zyc
 */
public class GsonUtil {

    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object src) {
        return GSON.toJson(src);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return GSON.toJson(src, typeOfSrc);
    }

    public static JsonElement toJsonElement(Object src) {
        return GSON.toJsonTree(src);
    }

    public static JsonElement toJsonElement(String src) {
        return new JsonParser().parse(src);
    }
}
