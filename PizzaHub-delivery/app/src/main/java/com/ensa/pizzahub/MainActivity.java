package com.ensa.pizzahub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ensa.pizzahub.adapter.AllMenuAdapter;
import com.ensa.pizzahub.adapter.CartAdapter;
import com.ensa.pizzahub.adapter.RecommendedAdapter;
import com.ensa.pizzahub.model.Allmenu;
import com.ensa.pizzahub.model.Pizza;
import com.ensa.pizzahub.model.Popular;
import com.ensa.pizzahub.model.Recommended;
import com.ensa.pizzahub.model.User;
import com.ensa.pizzahub.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView,cartRecyclerView;
    private final AppCompatActivity activity = MainActivity.this;
    CartAdapter cartAdapter;
    RecommendedAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(activity);
/*        try{
            dbHelper.addUser(new User("user1","user2@gmail.com","user123"));
        }
        catch (Exception e){
            System.out.println("sti");
        }*/
        dbHelper.setPizzas();


        setContentView(R.layout.activity_main);

        ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
        for(int i=0;i<10;i++){
            pizzaList.add(new Pizza("lorem ipsume", "Margarita",16.00,20,24,45,"C:\\Users\\hp\\Desktop\\GI3\\PizzaHub-Delivery\\PizzaHub-delivery\\app\\src\\main\\res\\drawable\\froma4.png"));
            //pizzaList.add(new Pizza());
        }
        getRecommendedData(pizzaList);
        //getAllMenu(pizzaList);
//        ArrayList<Popular> pizzaList = new ArrayList<Popular>();
//        for(int i=0;i<10;i++){
//            pizzaList.add(new Popular());
//        }
//        getMyCart(pizzaList);

        /*
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();


                getPopularData(foodDataList.get(0).getPopular());

                getRecommendedData(foodDataList.get(0).getRecommended());

                getAllMenu(foodDataList.get(0).getAllmenu());
                // lets run it.
                // we have fetched data from server.
                // now we have to show data in app using recycler view
                // lets make recycler view adapter
                // we have setup and bind popular section
                // in a same way we add recommended and all menu items
                // we add two adapter class for allmenu and recommended items.
                // so lets do it fast.

            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Server is not responding.", Toast.LENGTH_SHORT).show();
            }
        });
        */



    }

    private void  getRecommendedData(List<Pizza> recommendedList){

        recommendedRecyclerView = findViewById(R.id.recommended_recycler);
        recommendedAdapter = new RecommendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }

    private void  getAllMenu(List<Pizza> allmenuList){

        allMenuRecyclerView = findViewById(R.id.all_menu_recycler);
        allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();

    }

    private void  getMyCart(List<Popular> myCartList){

        cartRecyclerView = findViewById(R.id.history_view);
        cartAdapter = new CartAdapter(this, myCartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }
    // today w are going to make a food app like zomato and swiggy.
    // we have 3 category in data
    // popular items, recommended items and all menu,
    // lets have have a look on json data.
    // so lets start coding.
    // lets add retrofit dependency in gradle file for network call.
    // we have setup model class and adapter class
    // now we are going to setup data in recyclerview.
    // complited all recyclerview
    // now we will setup on click listener on items.
    // tutorial complited see you in the next video.
}
