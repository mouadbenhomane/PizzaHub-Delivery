package com.ensa.pizzahub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PizzaDetails extends AppCompatActivity {

    // now we will get data from intent and set to UI

    ImageView imageView;
    TextView itemName, itemPrice,itemDesc,pizzaCount;
    RadioGroup radioGroup;
    Button plus,minus;
    String name, priceS, priceM, priceL, imageUrl,desc;
    Double currentPrice;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_details);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        priceS = intent.getStringExtra("priceS");
        priceM = intent.getStringExtra("priceM");
        priceL = intent.getStringExtra("priceL");
        imageUrl = intent.getStringExtra("image");
        imageUrl = intent.getStringExtra("image");
        desc = intent.getStringExtra("description");

        itemDesc = findViewById(R.id.textView8);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price2);
        imageView = findViewById(R.id.pizzaImg);
        pizzaCount = findViewById(R.id.pizzaCount);
        plus = findViewById(R.id.addPizza);
        minus = findViewById(R.id.minusPizza);

        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        itemName.setText(name);
        itemPrice.setText(priceM);
        currentPrice=Double.parseDouble(priceM);
        itemDesc.setText(desc);
        pizzaCount.setText("1");

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(pizzaCount.getText().toString());
                count++;
                pizzaCount.setText(count+"");
                itemPrice.setText(String.format("%.2f",currentPrice*count));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(pizzaCount.getText().toString());
                if(count>1){
                    count--;
                    pizzaCount.setText(count+"");
                    itemPrice.setText(String.format("%.2f",currentPrice*count));
                }
            }
        });

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int count = Integer.parseInt(pizzaCount.getText().toString());
                switch (i){
                    case R.id.small:
                        currentPrice=Double.parseDouble(priceS);
                        itemPrice.setText(String.format("%.2f",currentPrice*count));
                        break;
                    case R.id.medium:
                        currentPrice=Double.parseDouble(priceM);
                        itemPrice.setText(String.format("%.2f",currentPrice*count));
                        break;
                    case R.id.large:
                        currentPrice=Double.parseDouble(priceL);
                        itemPrice.setText(String.format("%.2f",currentPrice*count));
                        break;
                }
            }
        });

    }
}
