package com.example.wang.myapplication.Dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wang on 2015/5/4.
 */
public class ConsumeDao extends BaseDao{
    public static final String TABLE_NAME = "t_consume";

    public static final String ID = "id";
    public static final String COST = "cost";
    public static final String CATEGORY = "category";
    public static final String DESCRIBE_MSG = "describe_msg";
    public static final String TIME ="time";

    public static void onCreateTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS" + TABLE_NAME + "(" +
                    ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COST + "DOUBLE NOT NULL," +
                    CATEGORY + "VARCHAR(200) NULL," +
                    DESCRIBE_MSG + "VARCHAR(200) NULL," +
                    TIME + "TIMESTAMP NOT NULL" +")";
        db.execSQL(sql);
    }

    public static void onUpdateTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO db update
    }
}
