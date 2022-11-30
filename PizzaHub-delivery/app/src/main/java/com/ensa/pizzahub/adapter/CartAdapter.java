package com.ensa.pizzahub.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensa.pizzahub.DBHelper;
import com.ensa.pizzahub.R;
import com.ensa.pizzahub.model.OrderItem;
import com.ensa.pizzahub.model.User;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.OrderViewHolder> {

    private Context context;
    private User user;
    private List<OrderItem> orderItemList;
    private TextView totalPrice;
    private DBHelper dbHelper;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public CartAdapter(Context context, List<OrderItem> orderItemListList,TextView totalPrice,User user) {
        this.context = context;
        this.orderItemList = orderItemListList;
        this.totalPrice = totalPrice;
        this.user = user;
        dbHelper=new DBHelper(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_recycler_items, parent, false);
        return new OrderViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.name.setText(orderItemList.get(position).getPizza().getName());
        holder.quantity.setText("x "+ orderItemList.get(position).getQuantity());
        holder.size.setText(orderItemList.get(position).getSize().toString());
        holder.price.setText(String.format("%.2f", orderItemList.get(position).getPrice()));
        holder.time.setText(String.format("%.2f", orderItemList.get(position).getPizza().getDeliveryTime()));
        holder.desc.setText(orderItemList.get(position).getPizza().getDescription());
        Glide.with(context).load(orderItemList.get(position).getPizza().getImagePath()).into(holder.pizzaImage);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double currentTotal = calculatTotalPrice(orderItemList);
                Double toSubstract = orderItemList.get(position).getPrice();
                totalPrice.setText("Total : "+String.format("%.2f",currentTotal-toSubstract)+" MAD");
                dbHelper.deleteOrderItem(orderItemList.get(position));
                orderItemList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, orderItemList.size());
                holder.itemView.setVisibility(View.GONE);


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

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public  static class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView pizzaImage;
        TextView name,quantity,size,price,desc,time;
        Button remove;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.pizzaName);
            quantity = itemView.findViewById(R.id.quantity);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.pirce);
            desc = itemView.findViewById(R.id.desc);
            time = itemView.findViewById(R.id.timeD);
            pizzaImage = itemView.findViewById(R.id.pizzaPhoto);
            remove = itemView.findViewById(R.id.remove);

        }
    }
}
