package com.example.gifts_of_love;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ViewItems extends AppCompatActivity {

    GridView gridView;
    TextView txtTotalItems;
    Context context;
    private List<GiftItems> gifts;


    private DBConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        context = this;

        dbConnect = new DBConnect(this);

        txtTotalItems = findViewById(R.id.txtTotalItems);
        gridView = findViewById(R.id.gridView);
        gifts = new ArrayList<>();

        gifts = dbConnect.getItems();

        GiftAdapter adapter = new GiftAdapter(context, R.layout.single_card, gifts);
        gridView.setAdapter(adapter);

        //Get count from the table
        int total = dbConnect.countItems();
        txtTotalItems.setText("Total: " + total + " Items");

        //Retrieve data from particular item
        gridView.setOnItemClickListener((parent, view, position, id) -> {



            final GiftItems it = gifts.get(position);
            String code = String.valueOf(it.getItemCode());

          //Toast toast = Toast.makeText(context, "Position: " + position + " Code: " + code, Toast.LENGTH_SHORT);
          //toast.show();

          Intent intent = new Intent(this, Single_GiftItem.class);
          intent.putExtra("itemCode", code);
          startActivity(intent);

        });

        //Update or Delete a particular item
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final GiftItems it = gifts.get(position);
                CharSequence[] buttons = {"Update", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose an action...");
                builder.setItems(buttons, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if(which == 0) {
                           //Update
                           Intent intent = new Intent(context, InsertItem.class);
                           startActivity(intent);
                       }
                       else {
                           //Delete
                           AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

                           builder1.setTitle("Delete This Item?");
                           builder1.setMessage("Are you sure you want to delete this permanently?");
                           builder1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dbConnect.DeleteItems(it.getItemCode());

                                   Toast toast = Toast.makeText(context, "Deleted Successfully...", Toast.LENGTH_SHORT);
                                   toast.show();

                                   Intent intent = new Intent(context, ViewItems.class);
                                   startActivity(intent);
                               }
                           });

                           builder1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dialog.dismiss();
                               }
                           });
                           builder1.show();
                       }
                    }
                });
                builder.show();
                return true;
            }
        });

    }



}