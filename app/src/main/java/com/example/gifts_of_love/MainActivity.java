package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editUser, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View view){

        //editUser = findViewById(R.id.input_username);
        //editPassword = findViewById(R.id.inputPassword);

        //String username = editUser.getText().toString();
        //String password = editPassword.getText().toString();

        //admin login
        //if((Objects.equals(username, "admin")) && (Objects.equals(password, "admin123"))){
            Intent intent = new Intent(this, InsertItem.class);
            startActivity(intent);
        //}

    }

    }
