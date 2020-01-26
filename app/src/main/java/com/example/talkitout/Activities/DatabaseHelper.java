package com.example.talkitout.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.talkitout.Classes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public static final String MOOD_COL1 = "ID";
    public static final String MOOD_COL2 = "CLIENT_USER";
    public static final String MOOD_COL3 = "MESSAGE";
    public static final String MOOD_COL4 = "INTENSITY";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createClientTable = "CREATE TABLE " +  CLIENT_TABLE_NAME + " (USERNAME TEXT , " +
                " NAME TEXT , PASSWORD TEXT , PRACTITIONER TEXT)";
        db.execSQL(createClientTable);
        String createPractitionerTable = "CREATE TABLE " +  PRACTITIONER_TABLE_NAME + " (USERNAME TEXT , " +
                " PASSWORD TEXT)";
        db.execSQL(createPractitionerTable);
        String createMoodTable = "CREATE TABLE " +  MOOD_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " CLIENT_USER TEXT , MESSAGE TEXT , INTENSITY INTEGER)";
        db.execSQL(createMoodTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_TABLE_NAME);
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

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * FROM " + CLIENT_TABLE_NAME, null);
        return data;
    }

    public ArrayList<Client> getAllClients()
    {
        ArrayList<Client> clients = new ArrayList<Client>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from contacts", null );
        cursor.moveToFirst();

        try{
            while (cursor.moveToNext())
            {
                String username = cursor.getString(cursor.getColumnIndex(CLIENT_COL1));
                String name = cursor.getString(cursor.getColumnIndex(CLIENT_COL2));
                String password = cursor.getString(cursor.getColumnIndex(CLIENT_COL3));
                String practitioner = cursor.getString(cursor.getColumnIndex(CLIENT_COL3));
                clients.add(new Client(username,name,password));
            }
        }
        finally
        {
            db.close();
        }

        return clients;
    }


}
