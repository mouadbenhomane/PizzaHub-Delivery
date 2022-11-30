package com.ensa.pizzahub;

import static com.ensa.pizzahub.model.ItemSize.MEDIUM;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.ensa.pizzahub.model.State;
import com.ensa.pizzahub.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    private final AppCompatActivity activity = CartActivity.this;
    CartAdapter cartAdapter;
    List<OrderItem> orderItemList = new ArrayList<OrderItem>();
    private User user;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);
        dbHelper = new DBHelper(activity);
        user = getIntent().getParcelableExtra("user");
        user = dbHelper.updateUserOrders(user);
        orderItemList = user.getOrder().getItems();
        System.out.println("==========OrderItemList==========");
        System.out.println(orderItemList);
        System.out.println("=================================");
        TextView totalPrice = findViewById(R.id.totalPrice);
        Button purshase = findViewById(R.id.purchase2);

        if(orderItemList.size()!=0) {
            Double price = calculatTotalPrice(orderItemList);
            totalPrice.setText("Total : "+String.format("%.2f",price)+" MAD");
            getMyCart(orderItemList);
            purshase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.getOrder().setState(State.CONFIRMED);
                    user.getOrder().setDate(new Date());
                    dbHelper.updateOrder(user.getOrder());
                    user = dbHelper.updateUserOrders(user);
                    finish();
                    Toast t = Toast.makeText(CartActivity.this, "Operation Successfully", Toast.LENGTH_SHORT);
                    t.show();
                }
            });
        }else{
            totalPrice.setText("Total : 0.00 MAD");
        }
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
        cartAdapter = new CartAdapter(this, myCartList,totalPrice,user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

}
