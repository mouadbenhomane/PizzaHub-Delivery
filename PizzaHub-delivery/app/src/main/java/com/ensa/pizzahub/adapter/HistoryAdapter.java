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
import com.ensa.pizzahub.model.OrderItem;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private List<OrderItem> historyList;

    public HistoryAdapter(Context context, List<OrderItem> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_recycler_items, parent, false);
         // here we need to create a layout for recyclerview cell items.


        return new HistoryViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String pizzaSize ="";
        switch(historyList.get(position).getSize()){
            case SMALL:
                pizzaSize = "Small";
                break;
            case MEDIUM:
                pizzaSize = "Medium";
                break;
            case LARGE:
                pizzaSize = "Large";
                break;
        }
        String orderItemState ="";
        switch(historyList.get(position).getOrder().getState()){
            case CONFIRMED:
                pizzaSize = "Confirmed";
                break;
            case CREATED:
                pizzaSize = "Created";
                break;
            case DELIVERED:
                pizzaSize = "Delivered";
                break;
        }
        holder.name.setText(historyList.get(position).getPizza().getName());
        holder.desc.setText(historyList.get(position).getPizza().getDescription());
        holder.price.setText(String.format("%.2f",historyList.get(position).getPrice()));
        holder.size.setText(pizzaSize);
        holder.quantity.setText(historyList.get(position).getQuantity()+" x");
        holder.state.setText(orderItemState);

        Glide.with(context).load(historyList.get(position).getPizza().getImagePath()).into(holder.itemImage);


    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public  static class HistoryViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView name,desc,price,size,quantity,time,state;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.pizzaName);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.prix);
            size = itemView.findViewById(R.id.size);
            quantity = itemView.findViewById(R.id.quantity);
            time = itemView.findViewById(R.id.time);
            state = itemView.findViewById(R.id.state);
            itemImage = itemView.findViewById(R.id.pizzaPhoto);

        }
    }
}
