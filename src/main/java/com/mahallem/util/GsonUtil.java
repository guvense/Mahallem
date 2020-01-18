package com.mahallem.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static Gson gson = null;

    public static <T> T convertToObject(String json, Class<T> clazz) {

        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }

        return gson.fromJson(json, clazz);

    }

    public static <T> String toJson(T clazz) {


        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }

        return gson.toJson(clazz);
    }
}
