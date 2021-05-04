package com.example.payment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String dbName = "Gift";
    private static final String tableName = "payment";

    private static final String ID = "id";
    private static final String cardNumber = "cardNumber";
    private static final String expDate = "expDate";
    private static final String cvv = "cvv";
    private static final String name = "name";

    public DBHandler(@Nullable Context context) {
        super(context, dbName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +tableName+ " ("
                        +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        +cardNumber+ " TEXT,"
                        +expDate+ " TEXT,"
                        +cvv+ " TEXT,"
                        +name+ " TEXT"
                        + ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropQuery = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(dropQuery);

        onCreate(db);

    }

    public void insertData(creditCard cc){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(cardNumber, cc.getCardNumber());
        contentValues.put(expDate, cc.getExpDate());
        contentValues.put(cvv, cc.getCvv());
        contentValues.put(name, cc.getName());

        sqLiteDatabase.insert(tableName, null, contentValues);
        sqLiteDatabase.close();

    }
}
