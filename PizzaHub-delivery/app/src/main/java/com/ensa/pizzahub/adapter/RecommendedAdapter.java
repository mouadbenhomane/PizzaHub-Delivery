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

import com.bumptech.glide.Glide;
import com.ensa.pizzahub.PizzaDetails;
import com.ensa.pizzahub.R;
import com.ensa.pizzahub.model.Pizza;
import com.ensa.pizzahub.model.Recommended;
import com.ensa.pizzahub.model.User;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    private Context context;
    private List<Pizza> recommendedList;
    private User user;

    public RecommendedAdapter(Context context, List<Pizza> recommendedList, User user) {
        this.context = context;
        this.recommendedList = recommendedList;
        this.user = user;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_recycler_items, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.recommendedName.setText(recommendedList.get(position).getName());
        holder.recommendedDeliveryTime.setText(String.format("%.2f",recommendedList.get(position).getDeliveryTime()));
        holder.recommendedPrice.setText(String.format("%.2f",recommendedList.get(position).getPrice_m()));
        Glide.with(context).load(recommendedList.get(position).getImagePath()).into(holder.recommendedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PizzaDetails.class);
                i.putExtra("pizza", recommendedList.get(position));
                i.putExtra("user", recommendedList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static class RecommendedViewHolder extends RecyclerView.ViewHolder{

        ImageView recommendedImage;
        TextView recommendedName, recommendedDeliveryTime, recommendedPrice;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.recommended_image);
            recommendedName = itemView.findViewById(R.id.recommended_name);
            recommendedDeliveryTime = itemView.findViewById(R.id.recommended_delivery_time);
            recommendedPrice = itemView.findViewById(R.id.recommended_price);

        }
    }


}
