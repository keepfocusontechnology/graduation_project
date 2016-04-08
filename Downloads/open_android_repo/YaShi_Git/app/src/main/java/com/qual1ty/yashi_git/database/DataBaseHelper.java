package com.qual1ty.yashi_git.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tianci on 16/4/5.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public final static int DB_VERSION = 1;

    public final static String DB_NAME = "userinfo";

    private final String CREATE_TABLE_USER = "create table if not exists usertable" +
            "(id integer primary key autoincrement" +
            ",username string not null" +
            ",password string not null"+
            ",nickname string not null)";

    private final String ADD_COLUMN = "alter table usertable " +
            "add column deleted integer default 0";

    public DataBaseHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public DataBaseHelper(Context context, int versionCode) {
        this(context, DB_NAME, null, versionCode);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        onUpgrade(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2)
            db.execSQL(ADD_COLUMN);
    }


}
