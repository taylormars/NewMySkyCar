package com.example.administrator.newmyskycar.MyData;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017-10-19 0019.
 */

public class SqlCarCargodata extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="sys";
    private final static int DATABASE_VERSION=2;
    //车货状态数据库
    public final static String USER_ID="_id";
    public final static String CarCargo_Destination="Destination";
    public final static String CarCargo_Time="Time";
    public final static String CarCargo_Model_car="Modelcar";
    public final static String CarCargo_Long_car="longcar";
    public final static String CarCargo_Level="Level";
    public final static String CarCargo_Number="Number";
    public final static String CarCargo_State="State";
    public final static String CarCargo_Price="Price";
    public final static String USER_NAMEID="userid";
    public final static String CarCargo_Friend="Friend";
    public final static String CarCargo_DATA="carcargo_data";
    public SqlCarCargodata(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+CarCargo_DATA+"("+USER_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_NAMEID + " TEXT,"+ CarCargo_Destination + " TEXT,"+ CarCargo_Friend + " TEXT,"+ CarCargo_Price + " TEXT,"+ CarCargo_State + " TEXT,"+ CarCargo_Number + " TEXT,"+ CarCargo_Level + " TEXT,"+ CarCargo_Long_car + " TEXT,"+ CarCargo_Time + " TEXT,"+ CarCargo_Model_car + " TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public long setServerAddress(String id,String carcargo_destination,String carcargo_time,String carcargo_model_car,
                                 String carcargo_long_car,String carcargo_level,String carcargo_number,String carcargo_state,
                                 String carcargo_price,String carcargo_friend)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAMEID, id);
        cv.put(CarCargo_Destination,carcargo_destination);
        cv.put(CarCargo_Time,carcargo_time);
        cv.put(CarCargo_Model_car,carcargo_model_car);
        cv.put(CarCargo_Long_car,carcargo_long_car);
        cv.put(CarCargo_Level,carcargo_level);
        cv.put(CarCargo_Number,carcargo_number);
        cv.put(CarCargo_State,carcargo_state);
        cv.put(CarCargo_Price,carcargo_price);
        cv.put(CarCargo_Friend,carcargo_friend);
        long row = db.insert(CarCargo_DATA, null, cv);
        return row;
    }
}
