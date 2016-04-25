package com.stpan.chitchat.utils;

import com.google.gson.Gson;

/**
 * 功能：
 * 创建时间:2016/4/7 20:41
 * 作者:pst
 */
public class GsonUtil {
    private static Gson gson = new Gson();
    private GsonUtil gsonUtil = null;

    private GsonUtil() {
    }

    public static Gson getGson(){
        return gson;
    }
}
