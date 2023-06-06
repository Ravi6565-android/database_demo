package com.example.database_demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class mydbhelper extends SQLiteOpenHelper {
    public mydbhelper( Context context) {
        super(context, "contact_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts (Id Integer primary key autoincrement,Name text,Number text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adddata(String name, String number) {
       SQLiteDatabase database=getWritableDatabase();
       database.execSQL("insert into contacts (name,number) values ('"+name+"','"+number+"')");

    }

    public void deletdata(int id) {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(" delete from contacts where id = "+id+" ");
    }

    public Cursor showall() {
        SQLiteDatabase database=getReadableDatabase();
        String view="select * from contacts";
        Cursor cursor=database.rawQuery("select * from contacts",null);
        return cursor;

    }

    public void updatedata(int id, String name, String number) {

        SQLiteDatabase database=getWritableDatabase();

String qur=" update contacts set name='"+name+"',number='"+number+"'where id="+id+" ";
        database.execSQL(qur);
    }
}
