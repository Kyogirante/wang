package com.example.wang.myapplication.Dao;

import android.database.Cursor;

/**
 * Created by wang on 2015/5/4.
 */
public abstract class BaseDao {

    protected static int getColumnIndex(Cursor cursor, String columnName){
        return cursor.getColumnIndex(columnName);
    }

    public static int getIntegerData(Cursor cursor, String columnName, int defaultValue){
        return getIntegerData(cursor,getColumnIndex(cursor,columnName),defaultValue);
    }

    public static int getIntegerData(Cursor cursor,int index, int defaultValue){
        try{
            if(cursor.isNull(index)){
                return defaultValue;
            }else {
                return cursor.getInt(index);
            }
        } catch (Exception e){
            return defaultValue;
        }
    }
    public static String getStringData(){
        return null;
    }

    public static double getDoubleData(){
        return 0f;
    }

    public static boolean getBooleanData(){
        return false;
    }

}
