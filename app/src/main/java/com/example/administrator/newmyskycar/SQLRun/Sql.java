package com.example.administrator.newmyskycar.SQLRun;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.MyData.SqlMydata;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-8-29 0029.
 */

public class Sql {
    private SQLiteDatabase db;
    private SqlMydata msqlmydata;
    private static Sql mrecordsDao;
    private static Activity activity;
    private SqlFrienddata msqlfrienddata;
    private SqlCarCargodata msqlcarcargodata;

    public Sql(Activity activity){
        msqlmydata = new SqlMydata(activity);
        msqlfrienddata = new SqlFrienddata(activity);
        msqlcarcargodata = new  SqlCarCargodata(activity);
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
    //验证用户登录
    public Integer checkLogin(String username,String password){
        Integer result=0;
        String sql = "select * from "+ SqlMydata.USER_DATA +" where "+SqlMydata.USER_NAME+"='"+username+"' and "+SqlMydata.USER_PASSWOED+"='"+password+"'";
        try{
            db = msqlmydata.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            result=cursor.getCount();
            return result;
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }finally{
            db.close();
            msqlmydata.close();
        }
    }
    //验证用户名是否重复
    public boolean CheckUsername(String username){
        Map<String,String> userMap= null;
        String sql="SELECT username FROM "+ SqlMydata.USER_DATA +" WHERE "+SqlMydata.USER_NAME+"='"+username+"'";
        db = msqlmydata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst() == false)
        {
            return true;
        }
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            String name = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_NAME));
            userMap.put("username ", name);
        }
        if (userMap.get(SqlMydata.USER_NAME).toString().equals(username))
        {
            return false;
        }
        return true;
    }
    //验证Userid是否重复
    public boolean CheckUserid(String userid){
        Map<String,String>userMap= null;
        String sql="SELECT userid FROM "+ SqlMydata.USER_DATA +" WHERE "+SqlMydata.USER_NAMEID+"='"+userid+"'";
        db = msqlmydata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst() == false)
        {
            return true;
        }
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            String id = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_NAMEID));
            userMap.put("userid ", id);
        }
        if(userMap.get(SqlMydata.USER_NAMEID).toString().equals(userid))
        {
            return false;
        }
        return true;
    }
    //获取好友信息List
    public List<Map<String,String>> getFriendList(String userid){
        List list=null;
        Map<String,String>friendMap= null;
        String sql="SELECT * FROM "+SqlFrienddata.Friend_DATA+" WHERE "+SqlFrienddata.USER_NAMEID+"='"+userid+"'";
        db = msqlfrienddata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst() == false){
            return list;
        }
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            String friendname = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Name));
            String friendphone = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Phone));
            String friendstate = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));

            friendMap.put(SqlFrienddata.Friend_Name, friendname);
            friendMap.put(SqlFrienddata.Friend_Phone, friendphone);
            friendMap.put(SqlFrienddata.Friend_State, friendstate);
            list.add(friendMap);
        }
        return list;
    }
    //获取空闲状态好友信息List
    public List<Map<String,String>> getFreeFriendList(String userid){
        List list=null;
        Map<String,String>friendMap= null;

        String sql="SELECT * FROM "+SqlFrienddata.Friend_DATA+" WHERE "+SqlFrienddata.USER_NAMEID+"='"+userid+"' And "+SqlFrienddata.Friend_State+"=’空闲’";
        db = msqlfrienddata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst() == false)
        {
            return list;
        }
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            String friendname = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Name));
            String friendphone = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Phone));
            String friendstate = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));

            friendMap.put(SqlFrienddata.Friend_Name, friendname);
            friendMap.put(SqlFrienddata.Friend_Phone, friendphone);
            friendMap.put(SqlFrienddata.Friend_State, friendstate);
            list.add(friendMap);
        }
        return list;
    }
    //获取状态为state的车货信息List
    public List<Map<String,String>> getcarstatusbyidandstate(String userid, String state){
        List list=null;
        Map<String,String>truckMap= null;

        String sql="SELECT * FROM "+SqlCarCargodata.CarCargo_DATA+" WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' And "+SqlCarCargodata.CarCargo_State+"='"+state+"'";
        db = msqlcarcargodata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst() == false)
        {
            return list;
        }
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            String Destination = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Destination));
            String Modelcar= cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Model_car));
            String longcar= cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Long_car));
            String Level = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Level));
            String Number = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Number));
            String State = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_State));
            String Price = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Price));
            String Friend = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Friend));

            truckMap.put(SqlCarCargodata.CarCargo_Destination, Destination);
            truckMap.put(SqlCarCargodata.CarCargo_Model_car, Modelcar);
            truckMap.put(SqlCarCargodata.CarCargo_Long_car, longcar);
            truckMap.put(SqlCarCargodata.CarCargo_Level, Level);
            truckMap.put(SqlCarCargodata.CarCargo_Number, Number);
            truckMap.put(SqlCarCargodata.CarCargo_State, State);
            truckMap.put(SqlCarCargodata.CarCargo_Price, Price);
            truckMap.put(SqlCarCargodata.CarCargo_Friend, Friend);

            list.add(truckMap);
        }
        return list;
    }
}
