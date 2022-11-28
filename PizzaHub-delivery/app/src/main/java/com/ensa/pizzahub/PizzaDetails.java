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
    TextView itemName, itemPrice,itemDesc;

    String name, priceS, priceM, priceL, imageUrl,desc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_details);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        priceM = intent.getStringExtra("priceM");
        imageUrl = intent.getStringExtra("image");
        imageUrl = intent.getStringExtra("image");
        desc = intent.getStringExtra("description");

        itemDesc = findViewById(R.id.textView8);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price2);
        imageView = findViewById(R.id.pizzaImg);

        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        itemName.setText(name);
        itemPrice.setText(priceM);
        itemDesc.setText(desc);

    }
}
