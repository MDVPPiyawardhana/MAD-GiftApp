package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserViewItems extends AppCompatActivity {

    GridView gridView;
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
        gridView = findViewById(R.id.gridView);
        gifts = new ArrayList<>();

        gifts = dbConnect.getItems();

        GiftAdapter adapter = new GiftAdapter(context, R.layout.single_card, gifts);
        gridView.setAdapter(adapter);

        //Get count from the table
        int total = dbConnect.countItems();
        txtTotalItems.setText("Total: " + total + " Items");

        //Retrieve data from particular item
        gridView.setOnItemClickListener((parent, view, position, id) -> {


            final GiftItems it = gifts.get(position);
            String code = String.valueOf(it.getItemCode());

            //Toast toast = Toast.makeText(context, "Position: " + position + " Code: " + code, Toast.LENGTH_SHORT);
            //toast.show();

            Intent intent = new Intent(this, UserSingleItemView.class);
            intent.putExtra("itemCode", code);
            startActivity(intent);

        });

    }
}