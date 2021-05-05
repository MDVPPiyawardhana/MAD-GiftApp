package com.example.gifts_of_love;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "Gifts";
    private static final String TABLE_NAME = "Items";

    //Column Names
    private static final String ITEM_CODE = "ItemCode";
    private static final String ITEM_NAME = "ItemName";
    private static final String PRICE = "Price";
    private static final String CATEGORY = "Category";
    private static final String IMAGE = "Images";
    private static final String DESCRIPTION = "Description";

    public DBConnect(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create database
        String CREATE_TABLE_QUERY = "CREATE TABLE " +TABLE_NAME+ " ("
                +ITEM_CODE+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ITEM_NAME+ " TEXT, "
                +PRICE+ " TEXT, "
                +CATEGORY+ " TEXT, "
                +IMAGE+ " TEXT, "
                +DESCRIPTION+ " TEXT);";

        //Run Query
        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop table if exists
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(DROP_TABLE_QUERY);

        //Create new table
        onCreate(db);
    }

    public void addItems(GiftItems giftItems){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM_NAME, giftItems.getItemName());
        contentValues.put(PRICE, giftItems.getPrice());
        contentValues.put(CATEGORY, giftItems.getCategory());
        contentValues.put(IMAGE, giftItems.getImages());
        contentValues.put(DESCRIPTION, giftItems.getDescription());

        //Insert Values to table
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();
    }

    //Count table records
    public int countItems(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

}
