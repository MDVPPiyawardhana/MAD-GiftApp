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


        username = findViewById(R.id.input_username);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("Admin") && password.getText().toString().equals("12345")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            MainActivity.this
                    );
                    builder.setTitle("Log In Successful");

                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    Intent Intent = new Intent(MainActivity.this, InsertItem.class);
                    startActivity(Intent);
                }
                if(username.getText().toString().equals("User") && password.getText().toString().equals("12345")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            MainActivity.this
                    );
                    builder.setTitle("Log In Successful");

                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    Intent Intent = new Intent(MainActivity.this, UserViewItems.class);
                    startActivity(Intent);
                }

                else{
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password" , Toast.LENGTH_SHORT).show();

                }

            }


        });



    }

    /*public void onClickLogin(View view){

        Intent intent = new Intent(this, InsertItem.class);
        startActivity(intent);

    }*/
}