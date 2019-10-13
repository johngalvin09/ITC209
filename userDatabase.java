package com.example.liftr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class userDatabase  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDatabase.db";

    //Table Name
    public static final String TABLE_USERDATA = "userData";
    //Table Columns
    public static final String COLUMN_USERID = "_userid";
    public static final String COLUMN_EXERCISE_ID = "exerciseID";
    public static final String COLUMN_DATE_ID = "dateLifted";
    public static final String COLUMN_WEIGHT_LIFTED = "weightLifted";

    String maxRecord;
    String lastRecord;




    public userDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("john","Datbase Creation begins");
        //Setting up the database, include all columns tha you need.
        String query = "CREATE TABLE " + TABLE_USERDATA + "(" +
                COLUMN_USERID + " INTEGER PRIMARY KEY," +
                COLUMN_EXERCISE_ID + " INTEGER," +
                COLUMN_DATE_ID + " TEXT, " +
                COLUMN_WEIGHT_LIFTED + " FLOAT" +
                ");";


        db.execSQL(query);
        Log.i("john","Database was created ok");





    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERDATA);
        onCreate(db);
    }




    public void addRecord(Integer userid, Integer exerciseID, double weight){
        SQLiteDatabase db = getWritableDatabase();

        Log.i("john","Adding User Record: ");
        SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-DD");
        long millis=System.currentTimeMillis();
        java.sql.Date date =new java.sql.Date(millis);

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERID,userid);
        values.put(COLUMN_EXERCISE_ID,exerciseID);
        values.put(COLUMN_DATE_ID,dtf.format(date));
        values.put(COLUMN_WEIGHT_LIFTED,weight);
        db.insert(TABLE_USERDATA,null,values);
        Log.i("john",Integer.toString(userid));
        Log.i("john",Integer.toString(exerciseID));
        Log.i("john",Double.toString(weight));
        db.close();
    }

    public String getMaxRecord(Integer userid, Integer exerciseId){
        Log.i("john","getting max record2");
        SQLiteDatabase db = getWritableDatabase();
        String output;
        // FIX THIS TO BE A MAX QUERY ONCE YOU GET IT WORKING.
        String query = "SELECT * FROM " + TABLE_USERDATA + " WHERE " + COLUMN_USERID + " =1;";

        Log.i("john",query);

        Cursor c = db.rawQuery(query,null);
        Log.i("john",Integer.toString(c.getCount()));
        c.moveToFirst();
        if(c.getCount()>0){
            maxRecord = c.getString(c.getColumnIndex(COLUMN_WEIGHT_LIFTED));

//            Log.i("john",maxRecord);
//            maxRecord = c.getString(c.getColumnIndex(COLUMN_DATE_ID));
//            Log.i("john",maxRecord);
        }

        db.close();
        return maxRecord;
        //return output;








    }

    public String getLastRecord(Integer userid, Integer exerciseId){
        Log.i("john","getting max record2");
        SQLiteDatabase db = getWritableDatabase();
        String output;
        // FIX THIS TO BE A MAX QUERY ONCE YOU GET IT WORKING.
        String query = "SELECT * FROM " + TABLE_USERDATA + " WHERE " + COLUMN_USERID + " =1;";
        Log.i("john",query);

        Cursor c = db.rawQuery(query,null);
        Log.i("john",Integer.toString(c.getCount()));
        c.moveToFirst();
        if(c.getCount()>0){
            maxRecord = c.getString(c.getColumnIndex(COLUMN_WEIGHT_LIFTED));

//            Log.i("john",maxRecord);
//            maxRecord = c.getString(c.getColumnIndex(COLUMN_DATE_ID));
//            Log.i("john",maxRecord);
        }

        db.close();
        return maxRecord;



    }

    public void testPrint(){

        Log.i("john","Test print method:\n ");
        //createDefaultExerciseList();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERDATA +";";
        Log.i("john",query);

        Cursor c = db.rawQuery(query,null);
        Log.i("john","c count is: " + Integer.toString(c.getCount()));






        c.moveToFirst();
        while(!c.isAfterLast()){


//        if(c.getCount()>0){

//                if(c.getString(c.getColumnIndex("_userid"))!=null) {
                    Log.i("john", "ID IS: " + c.getString(c.getColumnIndex("_userid")));
                    Log.i("john", "Bodypart IS: " + c.getString(c.getColumnIndex("exerciseID")));
                    Log.i("john", "exercisename IS: " + c.getString(c.getColumnIndex("dateLifted")));
                    Log.i("john", "Modifier IS: " + c.getString(c.getColumnIndex("weightLifted")));
                    //Log.i("john",output + " and it's id is: " + Integer.toString(c.getColumnIndex("_id")));
//                }
//
                c.moveToNext();
            }




        db.close();


    }


    public void createDefaultExerciseList(){
        Log.i("john","Creating default exercise list");
        addRecord(1,1,50.0);
        addRecord(1,2,15.0);
        addRecord(1,3,10.0);
        addRecord(1,4,30.0);
        addRecord(1,5,15.0);
        addRecord(1,6,20.0);
        addRecord(1,7,25.0);
        addRecord(1,8,13.0);
        addRecord(1,9,24.0);
        addRecord(1,10,32.0);
        addRecord(1,11,246.0);
        addRecord(1,12,6.0);
        addRecord(1,13,218.0);
        addRecord(1,12,19.0);
        addRecord(1,11,220.0);
        addRecord(1,11,234.0);
        addRecord(1,11,30.0);
        addRecord(1,11,40.0);
        addRecord(1,11,50.0);
        addRecord(1,1,20.0);



    }

    public void tableDeleter(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDATA);
        onCreate(db);

    }








}

