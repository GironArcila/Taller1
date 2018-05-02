package com.example.pc_win_10.taller.Connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {


    String query="create table usuarios " + "(cedula TEXT PRIMARY KEY,name TEXT,email TEXT, password TEXT ,tipo TEXT);";

    String query2 = "create table estudiantes " + "(cedula TEXT PRIMARY KEY,name TEXT,email TEXT);";

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String query="create table usuarios " + "(cedula TEXT PRIMARY KEY,name TEXT,email TEXT, password TEXT ,tipo TEXT);";
        //String query2 = "create table estudiantes " + "(cedula TEXT PRIMARY KEY,name TEXT,email TEXT);";
        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
