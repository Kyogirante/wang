package com.example.wang.myapplication.Dao;

import android.database.Cursor;

import com.example.wang.myapplication.Utils.Strings;

/**
 * Created by wang on 2015/5/4.
 */
public abstract class BaseDao {

    protected static int getColumnIndex(Cursor cursor, String columnName){
        return cursor.getColumnIndex(columnName);
    }

    public static int getIntegerData(Cursor cursor, String columnName, int defaultValue){
        return getIntegerData(cursor, getColumnIndex(cursor, columnName), defaultValue);
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

    public static String getStringData(Cursor cursor, String columnName, String defaultValue){
        return getStringData(cursor, getColumnIndex(cursor,columnName),defaultValue);
    }

    public static String getStringData(Cursor cursor, int index, String defaultValue){
        try{
            if(cursor.isNull(index)){
                return defaultValue;
            }else {
                String string = cursor.getString(index);
                string = Strings.emptyToNull(string);
                return string;
            }
        } catch (Exception e){
            return defaultValue;
        }
    }

    public static double getDoubleData(Cursor cursor, String columnName, Double defaultValue){
        return getDoubleData(cursor,getColumnIndex(cursor,columnName),defaultValue);
    }

    public static double getDoubleData(Cursor cursor, int index, Double defaultValue){
        try{
            if(cursor.isNull(index)){
                return defaultValue;
            } else {
                return cursor.getDouble(index);
            }
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static Long getLongData(Cursor cursor, String columnName, Long defaultValue){
        return getLongData(cursor,getColumnIndex(cursor,columnName),defaultValue);
    }

    public static Long getLongData(Cursor cursor, int index, Long defaultValue){
        try{
            if(cursor.isNull(index)){
                return defaultValue;
            } else {
                return cursor.getLong(index);
            }
        } catch (Exception e){
            return defaultValue;
        }
    }

}
