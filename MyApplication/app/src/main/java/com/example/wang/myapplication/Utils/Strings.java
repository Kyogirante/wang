package com.example.wang.myapplication.Utils;

/**
 * Created by wang on 2015/5/5.
 */
public class Strings {
    public static String emptyToNull(String string){
        return isNulOrEmpty(string) ? null : string;
    }

    public static boolean isNulOrEmpty(String string){
        return string == null || string.length() == 0;
    }
}
