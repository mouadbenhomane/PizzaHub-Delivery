package com.ensa.pizzahub;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensa.pizzahub.adapter.HistoryAdapter;
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
    List<Order> orderHistoryList = new ArrayList<Order>();
    private User user;
    private DBHelper dbHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        context =this;
        dbHelper = new DBHelper(activity);
        user = getIntent().getParcelableExtra("user");
        user = dbHelper.updateUserOrders(user);
        orderHistoryList =user.getOrderHistory();

        getMyHistory(orderListToOrderItemList(orderHistoryList));
    }

    private List<OrderItem> orderListToOrderItemList(List<Order> myOrderList){
        List<OrderItem> list = new ArrayList<OrderItem>();
        for (Order order : myOrderList){
            for(OrderItem orderItem : order.getItems()){
                orderItem.setDate(order.getDate());
                list.add(orderItem);
            }
        }

        return list;
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
