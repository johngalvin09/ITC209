package com.example.liftr;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    userDatabase uDB;
    workoutDatabase workOutDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create my exercise database
        dbHandler = new MyDBHandler(this,null,null, 5);
        uDB = new userDatabase(this,null,null, 1);
        workOutDB = new workoutDatabase(this,null,null,1);
        //Fill with some random data
        dbHandler.tableDeleter();
        dbHandler.createDefaultExerciseList();

        uDB.createDefaultExerciseList();
        uDB.addRecord(1,1,15.00);



    }



    public void workout_menu_clicked(View view) {
        //This allows you to switch to another activity
        Intent i = new Intent(this,doWorkout_ACTIVITY.class);
        startActivity(i);

    }

    public void sevenday_menu_clicked(View view) {
        //This allows you to switch to another activity
        Intent i = new Intent(this,sevenDaySummary_ACTIVITY.class);
        startActivity(i);
    }

    public void modify_menu_clicked(View view) {
        //This allows you to switch to another activity
        Intent i = new Intent(this,chooseDate_ACTIVITY.class);
        startActivity(i);
    }

    public void databaseTESTER(View view) {
        Log.i("john","Button pressed here");
        Exercises exercise = new Exercises("Tricep","Pushdown","NarrowGrip");
        dbHandler.addExercise(exercise);
        Log.i("john","Exercise added");


    }


    public void printDB(View view) {
        Log.i("john","just start");
//        Log.i("john","Test printing database");
////        dbHandler.testPrint();
////        Log.i("john","Completed fine");
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS.SSS");
        SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-DD");
        long millis=System.currentTimeMillis();
        java.sql.Date date2 =new java.sql.Date(millis);
        Log.i("john",dtf.format(date2));

        dbHandler.testPrint();
//        uDB.addRecord(1,13,45);
//        uDB.addRecord(1,11,75);
//        uDB.addRecord(1,8,12);




    }

    public void deleteTable(View view){
        dbHandler.tableDeleter();

    }


    //Log.i("john","");
    public void getIdFromExerciseString2(View view) {
//        String mystring = dbHandler.getIdFromString("Shoulder Side Lateral Raise Behind Back2");
//        Log.i("john",mystring + " fuck");

        workOut mine = new workOut("2019/10/13",1,3,5,20);
        workOutDB.addRecord(mine);


    }
}
