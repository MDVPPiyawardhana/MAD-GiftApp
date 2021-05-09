package com.example.gifts_of_love;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

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

        imageView = findViewById(R.id.imageView);

        if(resultCode == RESULT_OK && requestCode == Image){
            imageView.setImageURI(data.getData());

        }
    }

    public void OnClickViewBtn(View view){
        Intent intent = new Intent(this, ViewItems.class);
        startActivity(intent);
    }

    public void onClickAdd(View view){


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
        byte[] image = imageViewToByte(imageView);

        GiftItems giftItems = new GiftItems(itemName, price, category, image, description);
        dbConnect.addItems(giftItems);

        Toast toast = Toast.makeText(this, "Successfully Added...", Toast.LENGTH_SHORT);
        toast.show();
        editTextItemName.setText("");
        editTextPrice.setText("");
        editTextCategory.setText("");
        editTextDescription.setText("");
        imageView.setImageResource(R.drawable.image_gallary);

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}