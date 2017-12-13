package com.example.bwie.mydemo.http;

import com.google.gson.Gson;



public class GsonUtils {
    private static Gson instance;

    private GsonUtils() {

    }

    public static Gson getInstance() {
        if (instance == null) {
            instance = new Gson();
        }
        return instance;
    }
}
