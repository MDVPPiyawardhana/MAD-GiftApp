package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserCart extends AppCompatActivity {

    private DBConnect dbConnect;
    private Context context;
    TextView tCode, tName, tPrice, tTotal;
    EditText eQty;
    ImageView imageView;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cart);

        context = this;
        dbConnect = new DBConnect(context);

        tCode = findViewById(R.id.codeTxt);
        tName = findViewById(R.id.nameTxt);
        tPrice = findViewById(R.id.priceTxt);
        eQty = findViewById(R.id.editQty);
        tTotal = findViewById(R.id.totalTxt);
        imageView = findViewById(R.id.imageView2);
        calculate = findViewById(R.id.calculation);

        Intent intent = getIntent();
        String code = intent.getStringExtra("itemCode");
        int id = Integer.parseInt(code);
        String name = intent.getStringExtra("itemName");
        String price = intent.getStringExtra("itemPrice");

        tCode.setText(String.valueOf(id));
        tName.setText(name);
        tPrice.setText(price);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(eQty.getText().toString());
                double unitPrice = Double.parseDouble(price);

                double total = unitPrice * quantity;

                tTotal.setText(String.valueOf(total));
            }
        });


    }
}