package com.hyl.finallyapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table courses(" +
                "cid integer primary key autoincrement," +
                "course_name text," +
                "teacher text," +
                "class_room text," +
                "day integer," +
                "class_start integer," +
                "class_end integer)");
        db.execSQL("create table Users(" +
                "uid integer primary key autoincrement," +
                "username text," +
                "password text," +
                "cid integer," +
                " foreign key(cid)references courses (cid))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
