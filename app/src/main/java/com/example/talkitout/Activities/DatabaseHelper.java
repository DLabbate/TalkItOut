package com.example.talkitout.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.talkitout.Classes.*;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myList.db";
    public static final String CLIENT_TABLE_NAME = "Clients_Data";
    public static final String PRACTITIONER_TABLE_NAME = "Practitioner_Data";
    public static final String MOOD_TABLE_NAME = "Mood_Data";

    public static final String CLIENT_COL1 = "USERNAME";
    public static final String CLIENT_COL2 = "NAME";
    public static final String CLIENT_COL3 = "PASSWORD";
    public static final String CLIENT_COL4 = "PRACTITIONER";

    public static final String PRACTITIONER_COL1 = "USERNAME";
    public static final String PRACTITIONER_COL2 = "PASSWORD";
    public static final String PRACTITIONER_COL3 = "NAME";

    public static final String MOOD_COL1 = "ID";
    public static final String MOOD_COL2 = "CLIENT_USER";
    public static final String MOOD_COL3 = "MESSAGE";
    public static final String MOOD_COL4 = "INTENSITY";
    public static final String MOOD_COL5 = "DATE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        context.deleteDatabase(DATABASE_NAME);
        }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createClientTable = "CREATE TABLE " +  CLIENT_TABLE_NAME + " (USERNAME TEXT , " +
                " NAME TEXT , PASSWORD TEXT , PRACTITIONER TEXT)";
        db.execSQL(createClientTable);
        String createPractitionerTable = "CREATE TABLE " +  PRACTITIONER_TABLE_NAME + " (USERNAME TEXT , " +
                " PASSWORD TEXT , NAME TEXT)";
        db.execSQL(createPractitionerTable);
        String createMoodTable = "CREATE TABLE " +  MOOD_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " CLIENT_USER TEXT , MESSAGE TEXT , INTENSITY INTEGER, DATE date)";
        db.execSQL(createMoodTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("AHOY MATEY");
        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PRACTITIONER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MOOD_TABLE_NAME);
    }


    /*
    public boolean addData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);// Since that's where this item is being stored.
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }

     */

    public boolean addClientData(String username, String name, String password, String practitioner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLIENT_COL1, username);// Since that's where this item is being stored.
        contentValues.put(CLIENT_COL2, name);
        contentValues.put(CLIENT_COL3, password);
        contentValues.put(CLIENT_COL4, practitioner);


        long result = db.insert(CLIENT_TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean addMoodData(Integer id, String client_user, String message, Integer intensity, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOOD_COL1, id);// Since that's where this item is being stored.
        contentValues.put(MOOD_COL2, client_user);
        contentValues.put(MOOD_COL3, message);
        contentValues.put(MOOD_COL4, intensity);
        contentValues.put(MOOD_COL5, date.toString());


        long result = db.insert(MOOD_TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }


    public boolean addPractitionerData(String username, String password, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRACTITIONER_COL1, username);// Since that's where this item is being stored.
        contentValues.put(PRACTITIONER_COL2, password);
        contentValues.put(PRACTITIONER_COL3, name);
        System.out.println("#########" + contentValues.toString());
        System.out.println(db.toString());
        long result = db.insert(PRACTITIONER_TABLE_NAME, null, contentValues);
        System.out.println("#########" + result );
        if (result == -1){
            return false;
        }
        return true;
    }


    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * FROM " + CLIENT_TABLE_NAME, null);
        return data;
    }


    public ArrayList<Client> getAllClients()
    {
        ArrayList<Client> clients = new ArrayList<Client>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " +CLIENT_TABLE_NAME, null );
        cursor.moveToFirst();

        try{
            while (cursor.moveToNext())
            {
                String username = cursor.getString(cursor.getColumnIndex(CLIENT_COL1));
                String name = cursor.getString(cursor.getColumnIndex(CLIENT_COL2));
                String password = cursor.getString(cursor.getColumnIndex(CLIENT_COL3));
                String practitioner = cursor.getString(cursor.getColumnIndex(CLIENT_COL4));
                clients.add(new Client(username,name,password,practitioner));
            }
        }
        finally
        {
            db.close();
        }

        return clients;
    }

    public ArrayList<Practitioner> getAllPractitioners()
    {
        ArrayList<Practitioner> practitioners = new ArrayList<Practitioner>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " +PRACTITIONER_TABLE_NAME, null );
        cursor.moveToFirst();

        try{
            while (cursor.moveToNext())
            {
                String username = cursor.getString(cursor.getColumnIndex(PRACTITIONER_COL1));
                String password = cursor.getString(cursor.getColumnIndex(PRACTITIONER_COL2));
                String name = cursor.getString(cursor.getColumnIndex(PRACTITIONER_COL3));
                practitioners.add(new Practitioner(username,name,password));
            }
        }
        finally
        {
            db.close();
        }

        return practitioners;
    }

    public ArrayList<Mood> getAllMoods()
    {
        ArrayList<Mood> moods = new ArrayList<Mood>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + MOOD_TABLE_NAME, null );
        cursor.moveToFirst();


        try{
            while (cursor.moveToNext())
            {
                Integer id = cursor.getInt(cursor.getColumnIndex(MOOD_COL1));
                String client_username = cursor.getString(cursor.getColumnIndex(MOOD_COL2));
                String message = cursor.getString(cursor.getColumnIndex(MOOD_COL3));
                Integer intensity = cursor.getInt(cursor.getColumnIndex(MOOD_COL4));
                //String date = cursor.getString(cursor.getColumnIndex(MOOD_COL5));
                //try {
                //Date new_date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                //}
                //catch{

                //}

                moods.add(new Mood(id, client_username, message, intensity));
            }
        }
        finally
        {
            db.close();
        }

        return moods;
    }





}
