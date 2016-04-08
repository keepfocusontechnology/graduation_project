package com.qual1ty.yashi_git.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qual1ty.yashi_git.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tianci on 16/4/6.
 */
public class UserDao {

    DataBaseHelper helper;
    SQLiteDatabase db;

    public UserDao(Context context) {
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public UserDao(Context context, int versionCode) {
        helper = new DataBaseHelper(context, versionCode);
        db = helper.getWritableDatabase();
    }

    /**
     * sql 语句插入
     *
     * @param user
     */
    public void insert(User user) {
        String sql = "insert into usertable (username,nickname,password)values(?,?,?)";
        db.execSQL(sql, new String[]{user.username, user.nickName, user.psw});
        db.close();
    }

    /**
     * add user
     *
     * @param users
     */
    public void add(List<User> users) {
        db.beginTransaction();  //开始事务
        try {
            for (User user : users) {
                db.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)", new Object[]{user.username, user.psw, user.nickName});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * update user's psw
     *
     * @param person
     */
    public void updatePsw(User user) {
        ContentValues cv = new ContentValues();
        cv.put("password", user.psw);
        db.update("usertable", cv, "username = ?", new String[]{user.username});
    }

    /**
     * 根据用户名字删除用户
     *
     * @param user
     */
    public void deleteUser(User user) {
        db.delete("usertable", "username = ?", new String[]{String.valueOf(user.username)});
    }

    /**
     * query all users, return list
     *
     * @return List<User>
     */
    public List<User> query() {
        ArrayList<User> users = new ArrayList<>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            User user = new User();
            user.id = c.getInt(c.getColumnIndex("id"));
            user.username = c.getString(c.getColumnIndex("username"));
            user.psw = c.getString(c.getColumnIndex("password"));
            user.nickName = c.getString(c.getColumnIndex("nickname"));
            users.add(user);
        }
        c.close();
        return users;
    }

    /**
     * query all users, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM usertable", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }

    /**
     * 封装键值对插入
     *
     * @param user
     */
    public void insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.username);
        contentValues.put("nickname", user.nickName);
        contentValues.put("password", user.psw);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("usertable", null, contentValues);
        db.close();
    }

    public String getUserPsw(String name) {
        String psw = "";
        String sql = "SELECT password From usertable Where username=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{name});
        if (cursor.moveToNext()) {
            psw = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return psw;
    }

}
