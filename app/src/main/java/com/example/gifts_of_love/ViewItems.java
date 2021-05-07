package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewItems extends AppCompatActivity {

    private GridView listView;
    TextView txtTotalItems;
    Context context;
    private List<GiftItems> gifts;


    private DBConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        context = this;

        dbConnect = new DBConnect(this);

        txtTotalItems = findViewById(R.id.txtTotalItems);
        listView = findViewById(R.id.gridView);
        gifts = new ArrayList<>();

        gifts = dbConnect.getItems();

        GiftAdapter adapter = new GiftAdapter(context, R.layout.single_card, gifts);
        listView.setAdapter(adapter);

        //Get count from the table
        int total = dbConnect.countItems();
        txtTotalItems.setText("Total: " +total+ " Items");

    }
}