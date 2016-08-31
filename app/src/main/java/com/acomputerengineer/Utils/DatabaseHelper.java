package com.acomputerengineer.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String TABLE_USER = "user";
    static String KEY_USER_ID = "user_id";
    static String KEY_USER_FIRSTNAME = "user_first_name";
    static String KEY_USER_LASTNAME = "user_last_name";
    static String KEY_USER_AGE = "user_age";

    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USER_FIRSTNAME + " TEXT," + KEY_USER_LASTNAME + " TEXT," + KEY_USER_AGE + " INTEGER" + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public DatabaseHelper(Context c) {
        super(c, "db_name"/*db name*/, null, 1/*version*/);
    }

    //create user
    public long createUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_FIRSTNAME, u.getFirstName());
        values.put(KEY_USER_LASTNAME, u.getLastName());
        values.put(KEY_USER_AGE, u.getAge());
        long id = db.insert(TABLE_USER, null, values);
        db.close();
        return id;
    }

    //update user
    public void updateUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_FIRSTNAME, u.getFirstName());
        values.put(KEY_USER_LASTNAME, u.getLastName());
        values.put(KEY_USER_AGE, u.getAge());
        db.update(TABLE_USER, values, KEY_USER_ID + " = ?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    //delete user
    public void deleteUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_USER_ID + " = ?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    //read all users
    public ArrayList<User> readUsers(){
        ArrayList<User> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                User u = new User();
                u.setId(c.getInt((c.getColumnIndex(KEY_USER_ID))));
                u.setFirstName(c.getString(c.getColumnIndex(KEY_USER_FIRSTNAME)));
                u.setLastName(c.getString(c.getColumnIndex(KEY_USER_LASTNAME)));
                u.setAge(c.getInt(c.getColumnIndex(KEY_USER_AGE)));
                users.add(u);
            } while (c.moveToNext());
        }
        db.close();
        return users;
    }

    //read single user
    public User readUser(long id){
        ArrayList<User> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            do {
                User u = new User();
                u.setId(c.getInt((c.getColumnIndex(KEY_USER_ID))));
                u.setFirstName(c.getString(c.getColumnIndex(KEY_USER_FIRSTNAME)));
                u.setLastName(c.getString(c.getColumnIndex(KEY_USER_LASTNAME)));
                u.setAge(c.getInt(c.getColumnIndex(KEY_USER_AGE)));
                users.add(u);
            } while (c.moveToNext());
        }
        db.close();
        return users.get(0);
    }



    //user class
    public class User {
        private long id;
        private String firstName;
        private String lastName;
        private int age;

        public User() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}