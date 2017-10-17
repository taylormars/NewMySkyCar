package com.example.administrator.newmyskycar.MyData;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017-10-17 0017.
 */

public class SqlFrienddata extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="sys";
    private final static int DATABASE_VERSION=2;
    //用户信息数据库
    public final static String USER_ID="_id";
    public static String Friend_Name="friend_name";
    public static String Friend_Phone="friend_phone";
    public final static String USER_NAMEID="user_nameid";
    public final static String USER_DATA="friend_data";

    public SqlFrienddata(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+USER_DATA+"("+USER_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_NAMEID + " TEXT,"+ Friend_Name + " TEXT,"+ Friend_Phone + " TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public long setServerAddress(String friend_Name,String friend_Phone,String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAMEID, id);
        cv.put(Friend_Name, friend_Name);
        cv.put(Friend_Phone, friend_Phone);
        long row = db.insert(USER_DATA, null, cv);
        return row;
    }
}
