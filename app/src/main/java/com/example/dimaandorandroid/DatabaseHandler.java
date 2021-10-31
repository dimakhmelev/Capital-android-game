package com.example.dimaandorandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "appUsers";
    private static final String TABLE_USERS = "users";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SCORE = "score";
    private static final String KEY_LEVEL = "level";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_EMAIL + " TEXT PRIMARY KEY," + KEY_PASSWORD + " TEXT,"
                + KEY_SCORE + " TEXT,"+ KEY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addAppUser(AppUser appUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, appUser.getEmail());
        values.put(KEY_PASSWORD, appUser.getPassword());
        values.put(KEY_SCORE, appUser.getScore());
        values.put(KEY_LEVEL, appUser.getLevel());


        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    // code to get the single USER by email
    AppUser getAppUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_EMAIL,
                        KEY_PASSWORD, KEY_SCORE,KEY_LEVEL }, KEY_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        AppUser appUser = new AppUser(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3));
        // return contact
        return appUser;

    }
    public Boolean checkIfExist(String email) {
        List<AppUser> contactList = new ArrayList<AppUser>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int count=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                 AppUser contact = new AppUser();

                contact.setEmail(cursor.getString(0));
                contact.setPassword(cursor.getString(1));
                contact.setScore(cursor.getString(2));
                contact.setScore(cursor.getString(3));

                if( contact.getEmail().equals(email))
                {
                    count++;
                }
            } while (cursor.moveToNext());
        }

        // return contact list
        if (count>0){
            return true;
        }else{
            return false;
        }
    }

    // code to get all contacts in a list view
    public List<AppUser> getAllAppUsers() {
        List<AppUser> appUserList = new ArrayList<AppUser>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " ORDER BY "+ KEY_SCORE +" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AppUser appUser = new AppUser();
                appUser.setEmail(cursor.getString(0));
                appUser.setPassword(cursor.getString(1));
                appUser.setScore(cursor.getString(2));
                appUser.setLevel(cursor.getString(3));

                // Adding contact to list
                appUserList.add(appUser);
            } while (cursor.moveToNext());
        }

        // return contact list
        return appUserList;
    }




    public int updateContact(AppUser appUser) {
       SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, appUser.getScore());
        values.put(KEY_LEVEL, appUser.getLevel());

        // updating row
        return db.update(TABLE_USERS, values, KEY_EMAIL + " = ?",
                new String[] { appUser.getEmail() });

    }





}
