package com.ensa.pizzahub;

import static com.ensa.pizzahub.model.ItemSize.MEDIUM;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensa.pizzahub.adapter.AllMenuAdapter;
import com.ensa.pizzahub.adapter.CartAdapter;
import com.ensa.pizzahub.adapter.HistoryAdapter;
import com.ensa.pizzahub.adapter.RecommendedAdapter;
import com.ensa.pizzahub.model.Order;
import com.ensa.pizzahub.model.OrderItem;
import com.ensa.pizzahub.model.Pizza;
import com.ensa.pizzahub.model.User;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    private final AppCompatActivity activity = CartActivity.this;
    CartAdapter cartAdapter;
    List<Pizza> pizzaList;
    List<OrderItem> orderItemList = new ArrayList<OrderItem>();
    private User user;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);
        dbHelper = new DBHelper(activity);
        pizzaList = dbHelper.getAllPizza();
        user = getIntent().getParcelableExtra("user");
        for(Pizza p : pizzaList){
            orderItemList.add(new OrderItem(p,5,new Order(),20.00,MEDIUM));
        }
        TextView totalPrice = findViewById(R.id.totalPrice);
        Button purshase = findViewById(R.id.purchase2);
        Double price = calculatTotalPrice(orderItemList);
        totalPrice.setText("Total : "+String.format("%.2f",price)+" MAD");
        getMyCart(orderItemList);
        purshase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order(cartAdapter.getOrderItemListList());
            }
        });
    }
    private Double calculatTotalPrice(List<OrderItem> list){
        Double p=0.0;
        for (OrderItem o : list){
            p+=o.getPrice();
        }
        return p;
    }
    private void  getMyCart(List<OrderItem> myCartList){

        TextView totalPrice = findViewById(R.id.totalPrice);
        cartRecyclerView = findViewById(R.id.orderView);
        cartAdapter = new CartAdapter(this, myCartList,totalPrice);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

}
