package com.ensa.pizzahub.model;

public class OrderItem {
    private int id;
    private Pizza pizza;
    private int quantity;
    private Order order;
    private double price;
    public OrderItem() {
    }

    public OrderItem(Pizza pizza, int quantity, Order order, double price) {
        this.pizza = pizza;
        this.quantity = quantity;
        this.order = order;
        this.price = price;
    }

    public OrderItem(int id, Pizza pizza, int quantity, Order order, double price) {
        this.id = id;
        this.pizza = pizza;
        this.quantity = quantity;
        this.order = order;
        this.price = price;
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

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

