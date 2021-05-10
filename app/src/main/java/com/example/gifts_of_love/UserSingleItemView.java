package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserSingleItemView extends AppCompatActivity {

    ImageView imageView;
    TextView dName, dPrice, dCategory, dDescription;
    Button buttonBuy;
    private DBConnect dbConnect;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_single_item_view);

        context = this;
        dbConnect = new DBConnect(context);

        imageView = findViewById(R.id.displayImage);
        dName = findViewById(R.id.displayItemName);
        dPrice = findViewById(R.id.displayPrice);
        dCategory = findViewById(R.id.displayCategory);
        dDescription = findViewById(R.id.displayDescription);


        Intent intent = getIntent();
        String code = intent.getStringExtra("itemCode");
        int id = Integer.parseInt(code);


        dPrice.setText(String.valueOf(id));


        GiftItems giftItems = dbConnect.getSingleItem(id);

        byte[] image = giftItems.getImages();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);

        dName.setText(giftItems.getItemName());
        dPrice.setText(giftItems.getPrice());
        dCategory.setText(giftItems.getCategory());
        dDescription.setText(giftItems.getDescription());


        //Send data to another activity.........................

        buttonBuy = findViewById(R.id.btnBuy);

        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemCode = String.valueOf(giftItems.getItemCode());
                String itemName = giftItems.getItemName();
                String price = giftItems.getPrice();


                Intent intent = new Intent(context, UserCart.class);
                intent.putExtra("itemCode", itemCode);
                intent.putExtra("itemName", itemName);
                intent.putExtra("price", price);
                startActivity(intent);

            }
        });



    }


}
