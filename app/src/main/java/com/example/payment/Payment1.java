package com.example.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Payment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment1);

    }

    public void change(View view){

        Intent intent = new Intent(Payment1.this,changePaymentDetails.class);
        startActivity(intent);

    }

    public void deleteData(View view){

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("pay1");
        delRef.removeValue();

        Toast.makeText(getApplicationContext(), "Cancelled the payment", Toast.LENGTH_SHORT).show();

    }


}