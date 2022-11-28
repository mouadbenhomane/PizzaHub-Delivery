package com.ensa.pizzahub.model;

public class OrderItem {
    private int id;
    private Pizza pizza;
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Pizza pizza, int quantity) {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public OrderItem(int id, Pizza pizza, int quantity) {
        this.id = id;
        this.pizza = pizza;
        this.quantity = quantity;
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

