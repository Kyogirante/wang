package com.example.wang.myapplication.Dao;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by wang on 2015/5/11.
 */
public interface DataBaseBuilder<T> {
    public T build(Cursor cursor);
    public ContentValues deconstruct(T t);
}
