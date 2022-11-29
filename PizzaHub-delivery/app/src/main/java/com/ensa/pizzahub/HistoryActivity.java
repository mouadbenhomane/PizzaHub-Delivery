package com.ensa.pizzahub;

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
import com.ensa.pizzahub.model.ItemSize;
import com.ensa.pizzahub.model.Order;
import com.ensa.pizzahub.model.OrderItem;
import com.ensa.pizzahub.model.Pizza;
import com.ensa.pizzahub.model.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView historyRecyclerView;
    private final AppCompatActivity activity = HistoryActivity.this;
    HistoryAdapter historyAdapter;
    List<Pizza> pizzaList;
    List<OrderItem> orderItemList = new ArrayList<OrderItem>();
    private User user;
    private DBHelper dbHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        dbHelper = new DBHelper(activity);
        user=new User();
        dbHelper.setPizzas();
        context =this;

        for(Pizza p : pizzaList){
            orderItemList.add(new OrderItem(p,5,new Order(),20.00, ItemSize.MEDIUM));
        }
        getMyHistory(orderItemList);
    }


    private void  getMyHistory(List<OrderItem> myCartList){

        historyRecyclerView = findViewById(R.id.historyView);
        historyAdapter = new HistoryAdapter(this, myCartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        historyRecyclerView.setLayoutManager(layoutManager);
        historyRecyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
    }

}
