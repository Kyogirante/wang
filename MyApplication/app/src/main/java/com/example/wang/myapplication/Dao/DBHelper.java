package com.example.wang.myapplication.Dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wang on 2015/5/4.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "wang.db";
    private static final int DB_VERSION = 1;

    private static DBHelper mDBHelper = null;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static DBHelper getInstance(Context context){
        if(mDBHelper == null){
            synchronized (DBHelper.class){
                if(mDBHelper == null){
                    mDBHelper = new DBHelper(context);
                }
            }
        }
        return mDBHelper;
    }

    public synchronized static SQLiteDatabase getWritableDataBase(Context context){
        return getInstance(context).getWritableDatabase();
    }

    public synchronized static SQLiteDatabase getReadableDataBase(Context context){
        return getInstance(context).getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ConsumeDao.onCreateTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        ConsumeDao.onUpdateTable(sqLiteDatabase,oldVersion,newVersion);
    }
}
