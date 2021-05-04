package com.example.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class choosePaymentMeth extends AppCompatActivity {

    private ImageView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment_meth);

        button = findViewById(R.id.card);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choosePaymentMeth.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}