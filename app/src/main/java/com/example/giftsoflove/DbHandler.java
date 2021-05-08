package com.example.giftsoflove;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "shipping";
    private static final String TABLE_NAME = "shipping";

    //Column names
    private static final String FNAME = "fname";
    private static final String LNAME = "lname";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String ZIP = "zip";
    private static final String PHONE = "phone";




    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +FNAME+ "TEXT,"
                +LNAME+ "TEXT,"
                +ADDRESS+ "TEXT,"
                +CITY+ "TEXT,"
                +ZIP+ "TEXT,"
                +PHONE+ "INTEGER" +
                ");";


        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS  "+ TABLE_NAME;
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);


    }
}
