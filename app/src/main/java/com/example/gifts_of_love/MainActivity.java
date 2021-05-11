package com.example.gifts_of_love;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.inputusername);
        password = findViewById(R.id.inputEmail);
        login = findViewById(R.id.btnSign);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("Admin") && password.getText().toString().equals("12345")){

                    Toast toast = Toast.makeText(getApplicationContext(), "Login Successful...", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent Intent = new Intent(MainActivity.this, InsertItem.class);
                    startActivity(Intent);
                }
                if(username.getText().toString().equals("User") && password.getText().toString().equals("12345")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Login Successful...", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent Intent = new Intent(MainActivity.this, UserViewItems.class);
                    startActivity(Intent);
                }

                else{
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password" , Toast.LENGTH_SHORT).show();

                }

            }


        });



    }

    public void onClickSignUp(View view){

        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);

    }
}