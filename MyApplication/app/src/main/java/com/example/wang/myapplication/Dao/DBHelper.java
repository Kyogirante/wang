package com.example.wang.myapplication.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wang on 2015/5/4.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "test.db";
    public static final int DB_VERSION = 1;

    public static DBHelper dbHelper = null;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if(dbHelper == null){
            synchronized (DBHelper.class){
                if(dbHelper == null){
                    dbHelper = new DBHelper(context);
                }
            }
        }
        return dbHelper;
    }

    public synchronized static SQLiteDatabase getWritableDataBase(Context context){
        return getInstance(context).getWritableDatabase();
    }

    public synchronized static SQLiteDatabase getReadableDataBase(Context context){
        return getInstance(context).getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TODO 创建数据库
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO 升级数据库
    }
}
