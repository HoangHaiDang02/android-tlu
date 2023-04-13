package com.example.baitaplon;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class SqlLiteRepositries extends SQLiteOpenHelper {

    public SqlLiteRepositries(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //curd
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public boolean QueryDataTableCheckSelectUser(String sql) {
        SQLiteDatabase db  = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                return true;
            }
        } catch (Exception e) {
            Log.e("TAG", "Error querying database: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
