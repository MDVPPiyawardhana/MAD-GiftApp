package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editUser, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View view){

        editUser = findViewById(R.id.input_username);
        editPass = findViewById(R.id.inputPassword);

        String username = editUser.getText().toString();
        String password = editPass.getText().toString();


            Intent intent = new Intent(this, InsertItem.class);
            startActivity(intent);


    }

}
