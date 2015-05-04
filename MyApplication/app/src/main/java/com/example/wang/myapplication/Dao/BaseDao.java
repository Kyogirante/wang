package com.example.wang.myapplication.Dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wang on 2015/5/4.
 */
public abstract class BaseDao {
    public int getIntegerData(){
        return 0;
    }

    public String getStringData(){
        return null;
    }

    public double getDoubleData(){
        return 0f;
    }

    public boolean getBooleanData(){
        return false;
    }

    public abstract void onCreateTable(SQLiteDatabase db);

    public abstract void onUpdateTable(SQLiteDatabase db);
}