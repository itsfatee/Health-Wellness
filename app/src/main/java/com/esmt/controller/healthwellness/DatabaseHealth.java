package com.esmt.controller.healthwellness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHealth extends SQLiteOpenHelper {

    public DatabaseHealth(Context context){

        //Création de la base de données

        super(context,"DBHealth4.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //Création des tables de la bd

        //Table users
        db.execSQL("CREATE TABLE users(id_user INTEGER PRIMARY KEY AUTOINCREMENT, last_name VARCHAR(50), first_name VARCHAR(50), email VARCHAR(50),password VARCHAR(50), phone_number VARCHAR(50) );");

        //Table doctors
        db.execSQL("CREATE TABLE tdoctors(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), mail VARCHAR(50), number VARCHAR(50), specialty VARCHAR(50) );");

        //Table appointments
        db.execSQL("CREATE TABLE tappoints(id INTEGER PRIMARY KEY AUTOINCREMENT, last_name VARCHAR(50), first_name VARCHAR(50), email VARCHAR(50),date_appoint VARCHAR(50),time_appoint VARCHAR(50),doctor VARCHAR(50) );");

        //Table consultations
        db.execSQL("CREATE TABLE consult(id INTEGER PRIMARY KEY AUTOINCREMENT, last_name VARCHAR(50), first_name VARCHAR(50), doc_name VARCHAR(50),description VARCHAR(250) );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users;");
        db.execSQL("DROP TABLE IF EXISTS tdoctors;");
        db.execSQL("DROP TABLE IF EXISTS tappoints;");
        db.execSQL("DROP TABLE IF EXISTS consult;");
        onCreate(db); // creer une nouvelle table

    }

    public boolean addUser(String last_name, String first_name,String email,String password,String phone_number) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("last_name", last_name);
            cv.put("first_name", first_name);
            cv.put("email", email);
            cv.put("password", password);
            cv.put("phone_number", phone_number);

            db.insert("users", null, cv);
            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDoctor(String name, String mail,String number,String specialty) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("mail", mail);
            cv.put("number", number);
            cv.put("specialty", specialty);

            db.insert("tdoctors", null, cv);
            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAppoint(String last_name, String first_name, String email, String date_appoint, String time_appoint, String doctor) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("last_name", last_name);
            cv.put("first_name", first_name);
            cv.put("email", email);
            cv.put("date_appoint",date_appoint);
            cv.put("time_appoint",time_appoint);
            cv.put("doctor",doctor);

            db.insert("tappoints", null, cv);
            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addConsult(String last_name, String first_name, String doc_name, String description) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("last_name", last_name);
            cv.put("first_name", first_name);
            cv.put("doc_name", doc_name);
            cv.put("description",description);

            db.insert("consult", null, cv);
            db.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<HashMap<String, String>> GetDoctors(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> docList = new ArrayList<>();
        String query = "SELECT name, number, specialty FROM tdoctors ";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> doc = new HashMap<>();
            doc.put("name",cursor.getString(cursor.getColumnIndex("name")));
            doc.put("number",cursor.getString(cursor.getColumnIndex("number")));
            doc.put("specialty",cursor.getString(cursor.getColumnIndex("specialty")));
            docList.add(doc);
        }
        return  docList;
    }

    public ArrayList<HashMap<String, String>> GetAppoint(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> aptList = new ArrayList<>();
        String query = "SELECT last_name,first_name ,date_appoint, time_appoint FROM tappoints ";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> apt = new HashMap<>();
            apt.put("last_name",cursor.getString(cursor.getColumnIndex("last_name")));
            apt.put("first_name",cursor.getString(cursor.getColumnIndex("first_name")));
            apt.put("date_appoint",cursor.getString(cursor.getColumnIndex("date_appoint")));
            apt.put("time_appoint",cursor.getString(cursor.getColumnIndex("time_appoint")));
            aptList.add(apt);
        }
        return  aptList;
    }


    public ArrayList<HashMap<String, String>> GetConsult(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> consList = new ArrayList<>();
        String query = "SELECT last_name,first_name ,doc_name, description FROM consult ";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> apt = new HashMap<>();
            apt.put("last_name",cursor.getString(cursor.getColumnIndex("last_name")));
            apt.put("first_name",cursor.getString(cursor.getColumnIndex("first_name")));
            apt.put("doc_name",cursor.getString(cursor.getColumnIndex("doc_name")));
            apt.put("description",cursor.getString(cursor.getColumnIndex("description")));
            consList.add(apt);
        }
        return  consList;
    }


    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                "id_user"
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "email" + " = ?" + " AND " + "password" + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query("users", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
