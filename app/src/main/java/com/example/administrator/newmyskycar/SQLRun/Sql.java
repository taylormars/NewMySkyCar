package com.example.administrator.newmyskycar.SQLRun;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.newmyskycar.MyData.SqlMydata;

/**
 * Created by Administrator on 2017-8-29 0029.
 */

public class Sql {
    private SQLiteDatabase db;
    private SqlMydata mdbhelper;
    private static Sql mrecordsDao;
    private static Activity activity;

    public String USER_NID="_nid";
    public String USER_NNAME="user_nname";
    public String USER_NLV="user_nlv";
    public String Friend_Name="friend_name";
    public String Friend_Phone="friend_phone";


    public Sql(Activity activity){
        mdbhelper = new SqlMydata(activity);
    }
    public static Sql getInstance(Activity a){
        if(null == mrecordsDao){
            synchronized(Sql.class){
                if(null == mrecordsDao){
                    mrecordsDao = new Sql(a);
                }
            }
        }
        activity = a;
        return mrecordsDao;
    }
    //创建个人数据库
//    public void createself(SQLiteDatabase db1){
//        db1.execSQL("CREATE TABLE IF NOT EXISTS "+SqlMydata.USER_NAME+"("+USER_NID
//                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_NNAME + " TEXT,"+ USER_NLV + " TEXT);");
//    }
    //验证用户登录
    public Integer checkLogin(String username,String password){
        Integer result=0;
        String sql = "select * from "+ SqlMydata.USER_DATA +" where "+SqlMydata.USER_NAME+"='"+username+"' and "+SqlMydata.USER_PASSWOED+"='"+password+"'";
        try{
            db = mdbhelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            result=cursor.getCount();
            return result;
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }finally{
            db.close();
            mdbhelper.close();
        }
    }
//    public Cursor select(String username){
//        Cursor cursor = null;
//        try{
//            db = mdbhelper.getWritableDatabase();
//            String sql = "SELECT * FROM "+SqlMydata.USER_DATA+" where USER_NAME='"+username+"'";
//            cursor = db.rawQuery(sql, null);
//            return cursor;
//        }catch(Exception ex){
//            ex.printStackTrace();
//            return null;
//        }
//    }
}
