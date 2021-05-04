package com.example.payment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    EditText cardNumber, expDate, cvv, name;
    DatabaseReference dbRef;
    creditCard cd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText creditCard = (EditText) findViewById(R.id.creditCard);
        creditCard.addTextChangedListener(new CreditCardNumberFormattingTextWatcher());

        EditText date = (EditText) findViewById(R.id.dateOfExp);
        date.addTextChangedListener(new slashInDate());

        cardNumber = findViewById(R.id.creditCard);
        expDate = findViewById(R.id.dateOfExp);
        cvv = findViewById(R.id.securityCode);
        name = findViewById(R.id.cardholderName);

        cd = new creditCard();

        }

        public void clearControls(){

        cardNumber.setText("");
        expDate.setText("");
        cvv.setText("");
        name.setText("");

        }

    public void insertData(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");

        if(TextUtils.isEmpty(cardNumber.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter a card number", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(expDate.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter date of expire", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(cvv.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter cvv number", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(name.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter card holder's name", Toast.LENGTH_SHORT).show();

        else {

            cd.setCardNumber(cardNumber.getText().toString().trim());
            cd.setExpDate(expDate.getText().toString().trim());
            cd.setCvv(cvv.getText().toString().trim());
            cd.setName(name.getText().toString().trim());

            dbRef.child("pay1").setValue(cd);

            clearControls();

            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,Payment1.class);
            startActivity(intent);

        }

    }


    public static class CreditCardNumberFormattingTextWatcher implements TextWatcher {

        private boolean lock;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (lock || s.length() > 16) {
                return;
            }
            lock = true;
            for (int i = 4; i < s.length(); i += 5) {
                if (s.toString().charAt(i) != ' ') {
                    s.insert(i, " ");
                }
            }
            lock = false;
        }

    }

    public static class slashInDate implements TextWatcher {

        private boolean lock;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (lock || s.length() > 4) {
                return;
            }
            lock = true;
            for (int i = 2; i < s.length(); i += 3) {
                if (s.toString().charAt(i) != '/') {
                    s.insert(i, "/");
                }
            }
            lock = false;
        }

    }

}