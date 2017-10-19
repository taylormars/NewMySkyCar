package com.example.administrator.newmyskycar.MyData;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017-8-29 0029.
 */

public class SqlMydata extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="sys";
    private final static int DATABASE_VERSION=2;

    //用户信息数据库
    public final static String USER_DATA="user_data";
    public final static String USER_ID="_id";
    public final static String USER_NAMEID="user_id";
    public final static String USER_NAME="user_name";
    public final static String USER_PASSWOED="user_password";
    public final static String USER_PHONE="user_phone";
    public final static String USER_REALNAME="user_realname";
    public final static String USER_CITY="user_city";
    public final static String USER_ADDRESS="user_address";

    public SqlMydata(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+USER_DATA+"("+USER_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_NAMEID + " TEXT,"+ USER_NAME + " TEXT,"+ USER_PASSWOED + " TEXT,"+ USER_PHONE + " TEXT,"+ USER_REALNAME + " TEXT,"
                + USER_CITY + " TEXT," + USER_ADDRESS + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long setServerAddress(String name,String password,String id,String phone,String realname,String city,String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAMEID, id);
        cv.put(USER_NAME, name);
        cv.put(USER_PASSWOED, password);
        cv.put(USER_PHONE, phone);
        cv.put(USER_REALNAME, realname);
        cv.put(USER_CITY,city);
        cv.put(USER_ADDRESS,address);
        long row = db.insert(USER_DATA, null, cv);
        return row;
    }
}
