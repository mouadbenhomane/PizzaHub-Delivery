package com.ensa.pizzahub.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensa.pizzahub.PizzaDetails;
import com.ensa.pizzahub.R;
import com.ensa.pizzahub.model.Allmenu;
import com.ensa.pizzahub.model.Pizza;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    Context context;
    List<Pizza> allmenuList;

    public AllMenuAdapter(Context context, List<Pizza> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);

        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        /*holder.allMenuName.setText(allmenuList.get(position).getName());
        holder.allMenuPrice.setText("₹ "+allmenuList.get(position).getPrice());
        holder.allMenuTime.setText(allmenuList.get(position).getDeliveryTime());
        holder.allMenuRating.setText(allmenuList.get(position).getRating());
        holder.allMenuCharges.setText(allmenuList.get(position).getDeliveryCharges());
        holder.allMenuNote.setText(allmenuList.get(position).getNote());

        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.allMenuImage);
        */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PizzaDetails.class);
                i.putExtra("name", allmenuList.get(position).getName());
                i.putExtra("price", allmenuList.get(position).getPrice());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;
        ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuName = itemView.findViewById(R.id.pizzaName);
            allMenuNote = itemView.findViewById(R.id.desc);
            allMenuTime = itemView.findViewById(R.id.all_menu_deliverytime);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }

}
