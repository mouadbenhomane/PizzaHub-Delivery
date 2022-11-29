package com.ensa.pizzahub.model;

import java.util.Date;

public class OrderItem {
    private int id;
    private Pizza pizza;
    private int quantity;
    private Order order;
    private double price;
    private ItemSize size;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderItem(Pizza pizza, int quantity, Order order, double price, ItemSize size) {
        this.pizza = pizza;
        this.quantity = quantity;
        this.order = order;
        this.price = price;
        this.size = size;
    }

    public OrderItem(int id, Pizza pizza, int quantity, Order order, double price, ItemSize size) {
        this.id = id;
        this.pizza = pizza;
        this.quantity = quantity;
        this.order = order;
        this.price = price;
        this.size = size;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public OrderItem() {
    }

    public ItemSize getSize() {
        return size;
    }

    public void setSize(ItemSize size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", pizza=" + pizza +
                ", quantity=" + quantity +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}

