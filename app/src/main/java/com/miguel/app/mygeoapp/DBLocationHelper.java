package com.miguel.app.mygeoapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBLocationHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "app_geo.db";
    public static final int DATABASE_VERSION = 1;


    public DBLocationHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS positions " +
                "(" +
                " id INTEGER PRIMARY KEY, " +
                " lat REAL, " +
                " lng REAL " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS positions");
    }
}


