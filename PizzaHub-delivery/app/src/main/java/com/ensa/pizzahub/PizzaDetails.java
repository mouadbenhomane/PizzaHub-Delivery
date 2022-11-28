package com.ensa.pizzahub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PizzaDetails extends AppCompatActivity {

    // now we will get data from intent and set to UI

    ImageView imageView;
    TextView itemName, itemPrice, itemTime;

    String name, price, imageUrl,time;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_details);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        time = intent.getStringExtra("time");
        imageUrl = intent.getStringExtra("image");

        itemName = findViewById(R.id.recommended_name);
        itemPrice = findViewById(R.id.recommended_price);
        itemTime = findViewById(R.id.recommended_delivery_time);
        imageView = findViewById(R.id.recommended_image);

        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        itemName.setText(name);
        itemPrice.setText(price);

    }
}
