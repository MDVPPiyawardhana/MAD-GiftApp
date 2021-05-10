package com.example.gifts_of_love;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class UpdateItems extends AppCompatActivity {

    EditText editTextItemName, editTextPrice, editTextCategory, editTextDescription;
    ImageView imageView;
    Button btnUpdate;

    private DBConnect dbConnect;
    private Context context;
    private static final int Image = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_items);

        context = this;
        dbConnect = new DBConnect(context);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextDescription = findViewById(R.id.editTextDescription);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String code = intent.getStringExtra("itemCode");
        int id = Integer.parseInt(code);

        GiftItems giftItems = dbConnect.getSingleItem(id);

        //set values to input fields
        editTextItemName.setText(giftItems.getItemName());
        editTextPrice.setText(giftItems.getPrice());
        editTextCategory.setText(giftItems.getCategory());
        editTextDescription.setText(giftItems.getDescription());

        byte[] image = giftItems.getImages();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        imageView.setImageBitmap(bitmap);


    }

    //Update values
    public void OnClickUpdate(View view){

        Intent intent = getIntent();
        String code = intent.getStringExtra("itemCode");
        int id = Integer.parseInt(code);

        String itemName = editTextItemName.getText().toString();
        String price = editTextPrice.getText().toString();
        String category = editTextCategory.getText().toString();
        String description = editTextDescription.getText().toString();
        byte[] image = imageViewToByte(imageView);

        GiftItems items = new GiftItems(id, itemName,price, category, image, description);

        dbConnect.UpdateItems(items);

            Toast toast = Toast.makeText(context, "Updated Successfully...", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent1 = new Intent(context, ViewItems.class);
            startActivity(intent1);


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

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}