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
    //好友信息数据库
    public final static String USER_ID="_id";
    public static String Friend_Name="friendname";
    public static String Friend_Phone="friendphone";
    public final static String USER_NAMEID="userid";
    public final static String Friend_State="friendstate";
    public final static String Friend_DATA="friend_data";
    public SqlFrienddata(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+Friend_DATA+"("+USER_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_NAMEID + " TEXT,"+ Friend_Name + " TEXT,"+ Friend_State + " TEXT,"+ Friend_Phone + " TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public long setServerAddress(String friend_Name,String friend_Phone,String id,String friend_state)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAMEID, id);
        cv.put(Friend_Name, friend_Name);
        cv.put(Friend_Phone, friend_Phone);
        cv.put(Friend_State,friend_state);
        long row = db.insert(Friend_DATA, null, cv);
        return row;
    }
}
