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

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        /*
        holder.popularName.setText(popularList.get(position).getName());

        // for image we add Glide library dependency for image fetching from server

        Glide.with(context).load(popularList.get(position).getImageUrl()).into(holder.popularImage);
        */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PizzaDetails.class);

                context.startActivity(i);
            }
        });

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
