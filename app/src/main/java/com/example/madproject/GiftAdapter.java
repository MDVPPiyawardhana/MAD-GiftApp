package com.example.madproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GiftAdapter extends ArrayAdapter<GiftItems> {

    private Context context;
    private int resource;
    List<GiftItems> giftItems;

    GiftAdapter(Context context, int resource, List<GiftItems> giftItems){
        super(context,resource,giftItems);
        this.context = context;
        this.resource = resource;
        this.giftItems = giftItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View card = inflater.inflate(resource,parent,false);

        TextView textPrice = card.findViewById(R.id.textPrice);
        TextView btnItem = card.findViewById(R.id.btnItem);
        ImageView itemImage = card.findViewById(R.id.itemImage);

        GiftItems items = giftItems.get(position);
        textPrice.setText(items.getPrice());
        btnItem.setText(items.getItemName());

        byte[] image = items.getImages();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        itemImage.setImageBitmap(bitmap);

        return card;
    }
}