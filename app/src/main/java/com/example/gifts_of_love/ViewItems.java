package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewItems extends AppCompatActivity {

    TextView txtTotalItems;

    private DBConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        dbConnect = new DBConnect(this);

        txtTotalItems = findViewById(R.id.txtTotalItems);

        //Get count from the table
        int total = dbConnect.countItems();
        txtTotalItems.setText("Total: " +total+ " Items");

    }
}