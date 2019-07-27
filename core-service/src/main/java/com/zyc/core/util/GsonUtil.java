package com.zyc.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

}
