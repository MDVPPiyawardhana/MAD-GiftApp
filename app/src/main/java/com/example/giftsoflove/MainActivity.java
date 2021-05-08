package com.example.giftsoflove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText fname, lname, address, city, zip, phone;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        zip = findViewById(R.id.city);
        phone = findViewById(R.id.city);
        save = findViewById(R.id.button1);



    }

    public void  OnclickSave(View view){

        Toast toast = Toast.makeText(this, "Saved Successfully...", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, Shipping_Details.class);
        startActivity(intent);
    }
}