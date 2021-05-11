package com.example.gifts_of_love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText editName, editEmail, editUser, editPass;
    private  DBConnect dbConnect;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        context = this;
        dbConnect = new DBConnect(context);

        editName = findViewById(R.id.input_name);
        editEmail = findViewById(R.id.inputEmail);
        editUser = findViewById(R.id.inputusername);
        editPass = findViewById(R.id.inputPass);

    }

    public boolean validateName(){
        String name = editName.getText().toString();

        if (name.isEmpty()){
            editName.setError("Fields can't be empty");
            return false;
        }
        else{
            editName.setError(null);
            return true;
        }
    }
    public boolean validateEmail(){
        String email = editEmail.getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()){
            editEmail.setError("Fields can't be empty");
            return false;
        }
        else if(!email.matches(emailPattern)){
            editEmail.setError("Invalid Email");
            return false;
        }
        else{
            editName.setError(null);

            return true;
        }
    }
    public boolean validateUsername(){
        String username = editName.getText().toString();

        if (username.isEmpty()){
            editName.setError("Fields can't be empty");
            return false;
        }
        else{
            editName.setError(null);
            return true;
        }
    }
    public boolean validatePassword(){
        String password = editName.getText().toString();

        if (password.isEmpty()){
            editName.setError("Fields can't be empty");
            return false;
        }
        else{
            editName.setError(null);
            return true;
        }
    }

    public void OnClickSignButton(View view){

        if(!validateName() | !validateEmail() | validateUsername() | validatePassword()){
            return;
        }

            String name = editName.getText().toString();
            String email = editEmail.getText().toString();
            String username = editUser.getText().toString();
            String password = editPass.getText().toString();

            User user = new User(name, email, username, password);
            dbConnect.addUser(user);

            Toast toast = Toast.makeText(this, "Successfully Completed...", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


        }

    }
