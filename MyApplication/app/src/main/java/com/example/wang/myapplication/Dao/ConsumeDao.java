package com.example.wang.myapplication.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wang.myapplication.Bean.ConsumeBean;

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
                    TIME + "TIMESTAMP" +
                    ")";
        db.execSQL(sql);
    }

    public static void onUpdateTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 1){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreateTable(db);
        }
    }

    public synchronized static void insertConsume(Context context, ConsumeBean bean){
        SQLiteDatabase db = DBHelper.getWritableDataBase(context);
        db.beginTransaction();

        db.insertWithOnConflict(TABLE_NAME, null, new ConsumeBuild().deconstruct(bean), SQLiteDatabase.CONFLICT_REPLACE);

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public synchronized static void deleteConsume(Context context, ConsumeBean bean){
        SQLiteDatabase db = DBHelper.getWritableDataBase(context);
        db.beginTransaction();

        db.delete(TABLE_NAME, ID, new String[]{String.valueOf(bean.getId())});

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public synchronized static void updateConsume(Context context, ConsumeBean bean){
        SQLiteDatabase db = DBHelper.getWritableDataBase(context);
        db.beginTransaction();

        db.update(TABLE_NAME, new ConsumeBuild().deconstruct(bean), ID, new String[]{String.valueOf(bean.getId())});

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public synchronized static void queryConsume(Context context){
        SQLiteDatabase db = DBHelper.getReadableDataBase(context);

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if(cursor != null){

        }

    }

    public static class ConsumeBuild implements DataBaseBuilder<ConsumeBean>{

        @Override
        public ConsumeBean build(Cursor cursor) {
            return null;
        }

        @Override
        public ContentValues deconstruct(ConsumeBean consumeBean) {
            ContentValues values = new ContentValues();
            values.put(COST, consumeBean.getCost());
            values.put(CATEGORY, consumeBean.getCategory());
            values.put(DESCRIBE_MSG, consumeBean.getDescribe_msg());
            values.put(TIME, consumeBean.getTime());
            return values;
        }
    }
}
