package com.example.sign_up_page;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "signup.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table allusers(username TEXT primary Key,email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists allusers");
    }
    public  boolean insert(String username,String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = db.insert("allusers",null,contentValues);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean delete(String username){
            //get writable database and pass it to db
            SQLiteDatabase db = this.getWritableDatabase();
            //to put the values inside table
            //cursor is used to select a rows one by one
            Cursor cursor = db.rawQuery("select * from allusers where username=?", new String[]{username});
            if (cursor.getCount() > 0) {
                long result = db.delete("allusers", "username=?", new String[]{username});
                if (result == -1)
                    return false;
                else

                    return true;
            } else {
                return false;
            }
    }
    public Boolean update(String user,String email, String pass) {
//get writable database and pass it to db
        SQLiteDatabase db = this.getWritableDatabase();
        //to put the values inside table
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", pass);
        //cursor is used to select a rows one by one
        Cursor cursor = db.rawQuery("select * from allusers where username=?", new String[]{user});
        if (cursor.getCount() > 0) {
            long result = db.update("allusers", contentValues, "username=?", new String[]{user});
            if (result == -1)
                return false;
            else

                return true;
        } else {
            return false;
        }
    }
    public boolean checkusername(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from allusers where username=?",new String[]{username});

        if(cursor.getCount()>0)
        {
            return true;
        }else
        {
            return false;
        }
    }
    public boolean checkuseremail(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from allusers where email=?",new String[]{email});

        if(cursor.getCount()>0)
        {
            return true;
        }else
        {
            return false;
        }
    }

    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from allusers where username=? and password =?",new String[]{username,password});

        if(cursor.getCount() >0)
        {
            return true;
        }else{
            return false;
        }
    }
    
    
    public Cursor getdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from allusers",null);

        return  cursor;
    }

}
