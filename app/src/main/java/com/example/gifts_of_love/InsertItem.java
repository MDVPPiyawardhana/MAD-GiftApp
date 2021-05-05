package com.example.gifts_of_love;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InsertItem extends AppCompatActivity {

    EditText editTextItemName, editTextPrice, editTextCategory, editTextDescription;
    ImageView imageView;

    private static final int Image = 10;
    private  DBConnect dbConnect;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);
    }

    public void PickImage(View view){
        Intent intentPick = new Intent(Intent.ACTION_PICK);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
        intentPick.setData(Uri.parse(path));
        intentPick.setType("image/*");

        //Intent Chooser = Intent.createChooser(intentPick, "Select an Image...");
        startActivityForResult(intentPick, Image);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == Image){
            imageView.setImageURI(data.getData());

        }
    }

    public void onClickAdd(View view){

        Toast toast = Toast.makeText(this, "Successfully Added...", Toast.LENGTH_SHORT);
        toast.show();

        //Navigate Items View Page
        Intent intent = new Intent(this, ViewItems.class);
        startActivity(intent);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextDescription = findViewById(R.id.editTextDescription);
        imageView = findViewById(R.id.imageView);

        context = this;
        dbConnect = new DBConnect(context);

        String itemName = editTextItemName.getText().toString();
        String price = editTextPrice.getText().toString();
        String category = editTextCategory.getText().toString();
        String description = editTextDescription.getText().toString();
        String image = "";

        GiftItems giftItems = new GiftItems(itemName, price, category, image, description);
        dbConnect.addItems(giftItems);



    }


}