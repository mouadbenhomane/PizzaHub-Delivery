package com.ensa.pizzahub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ensa.pizzahub.adapter.AllMenuAdapter;
import com.ensa.pizzahub.adapter.CartAdapter;
import com.ensa.pizzahub.adapter.HistoryAdapter;
import com.ensa.pizzahub.adapter.RecommendedAdapter;
import com.ensa.pizzahub.model.ItemSize;
import com.ensa.pizzahub.model.Order;
import com.ensa.pizzahub.model.OrderItem;
import com.ensa.pizzahub.model.Pizza;
import com.ensa.pizzahub.model.State;
import com.ensa.pizzahub.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recommendedRecyclerView, allMenuRecyclerView,cartRecyclerView;
    private final AppCompatActivity activity = MainActivity.this;
    CartAdapter cartAdapter;
    HistoryAdapter historyAdapter;
    RecommendedAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;
    Button cartButton;
    List<Pizza> pizzaList;
    List<OrderItem> orderItemList = new ArrayList<OrderItem>();
    private User user;
    private DBHelper dbHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(activity);
        user=new User();
        dbHelper.setPizzas();

        setContentView(R.layout.activity_main);
        cartButton = findViewById(R.id.cartButton);

        pizzaList = dbHelper.getAllPizza();
        System.out.println("=================");
        System.out.println(dbHelper.getAllUser().toString());
        User user = new User("hamza","hamza@email.com","hamza123");
        try{
            dbHelper.addUser(user);
        }
        catch(Exception e){
            System.out.println(e);
        }
        user = dbHelper.updateUserOrders(user);
        //OrderItem item = new OrderItem(pizzaList.get(0),3,user.getOrder(),23.5, ItemSize.LARGE);
        user.getOrder().setState(State.CONFIRMED);
        user.getOrder().setDate(new Date());
        dbHelper.updateOrder(user.getOrder());
        user = dbHelper.updateUserOrders(user);
        System.out.println(user);
        System.out.println(pizzaList);
        System.out.println("=================");
        getRecommendedData(pizzaList);
        getAllMenu(pizzaList);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.my_cart);
                //TextView totalPrice = view.findViewById(R.id.totalPrice);
                //totalPrice.setText(String.format("%.2f",calculatTotalPrice(orderItemList)));
                getMyCart(orderItemList);
            }
        });
        //Toast.makeText(MainActivity.this, "Server is not responding.", Toast.LENGTH_SHORT).show();
    }
    private Double calculatTotalPrice(List<OrderItem> list){
        Double p=0.0;
        for (OrderItem o : list){
            p+=o.getPrice();
        }
        return p;
    }
    private void  getRecommendedData(List<Pizza> recommendedList){

        recommendedRecyclerView = findViewById(R.id.recommended_recycler);
        recommendedAdapter = new RecommendedAdapter(this, recommendedList,user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }

    private void  getAllMenu(List<Pizza> allmenuList){

        allMenuRecyclerView = findViewById(R.id.all_menu_recycler);
        allMenuAdapter = new AllMenuAdapter(this, allmenuList,user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();

    }

    private void  getMyCart(List<OrderItem> myCartList){

        cartRecyclerView = findViewById(R.id.orderView);
        cartAdapter = new CartAdapter(this, myCartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }

}
