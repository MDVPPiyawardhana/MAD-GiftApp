package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBConnect extends SQLiteOpenHelper {

    private static final int VERSION = 3;
    private static final String DB_NAME = "Gifts";
    private static final String TABLE_NAME = "Items";
    private static final String TABLE_NAME2 = "OrderedItems";

    //Table 01 - Column Names
    private static final String ITEM_CODE = "ItemCode";
    private static final String ITEM_NAME = "ItemName";
    private static final String PRICE = "Price";
    private static final String CATEGORY = "Category";
    private static final String IMAGE = "Images";
    private static final String DESCRIPTION = "Description";
    
    //Table 02 - Column Names
    private static final String ORDER_NO = "OrderNo";
    private static final String ITEM_CODE2 = "ItemCode";
    private static final String ITEM_NAME2 = "ItemName";
    private static final String PRICE2 = "Price";
    private static final String IMAGE2 = "Images";
    private static final String QUANTITY = "Quantity";

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
                +IMAGE+ " BLOB, "
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

    //Get Items from the table
    public List<GiftItems> getItems() {

        List<GiftItems> listItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                GiftItems items = new GiftItems();

                items.setItemCode(cursor.getInt(0));
                items.setItemName(cursor.getString(1));
                items.setPrice(cursor.getString(2));
                items.setCategory(cursor.getString(3));
                items.setImages(cursor.getBlob(4));
                items.setDescription(cursor.getString(5));

                listItems.add(items);
            } while (cursor.moveToNext());
        }
        return listItems;
    }

    //Get a single data
    public GiftItems getSingleItem(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{ITEM_CODE, ITEM_NAME, PRICE, CATEGORY, IMAGE, DESCRIPTION},ITEM_CODE + "=?", new String[]{String.valueOf(id)},null, null,null);

        GiftItems items;
        if (cursor != null){
            cursor.moveToFirst();
            items = new GiftItems(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getString(5)
            );
            return items;
        }
        return null;
    }

}