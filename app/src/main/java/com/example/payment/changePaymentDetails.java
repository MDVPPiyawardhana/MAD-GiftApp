package com.example.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class changePaymentDetails extends AppCompatActivity {

    EditText cardNumber, expDate, cvv, name;
    DatabaseReference dbRef;
    //creditCard cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_payment_details);

        EditText creditCard = (EditText) findViewById(R.id.inputCardNum);
        creditCard.addTextChangedListener(new MainActivity.CreditCardNumberFormattingTextWatcher());

        EditText date = (EditText) findViewById(R.id.inputDateExpire);
        date.addTextChangedListener(new MainActivity.slashInDate());

        cardNumber = findViewById(R.id.inputCardNum);
        expDate = findViewById(R.id.inputDateExpire);
        cvv = findViewById(R.id.inputSecurityCode);
        name = findViewById(R.id.inputName);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("pay1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren()){

                    cardNumber.setText(snapshot.child("cardNumber").getValue().toString());
                    expDate.setText(snapshot.child("expDate").getValue().toString());
                    cvv.setText(snapshot.child("cvv").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());

                }else
                    Toast.makeText(getApplicationContext(), "No data to Display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clearControls(){

        cardNumber.setText("");
        expDate.setText("");
        cvv.setText("");
        name.setText("");

    }

    public void updateData(View view){

        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.child("Payment").child("pay1").child("cardNumber").setValue(cardNumber.getText().toString().trim());
        dbRef.child("Payment").child("pay1").child("expDate").setValue(expDate.getText().toString().trim());
        dbRef.child("Payment").child("pay1").child("cvv").setValue(cvv.getText().toString().trim());
        dbRef.child("Payment").child("pay1").child("name").setValue(name.getText().toString().trim());

        clearControls();

        Toast.makeText(getApplicationContext(), "Update data successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(changePaymentDetails.this,Payment1.class);
        startActivity(intent);

    }

}