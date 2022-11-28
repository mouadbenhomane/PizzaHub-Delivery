package com.ensa.pizzahub.model;

import java.util.List;

public class Order {
    private int id;
    private State state;
    private List<OrderItem> items;

    public Order() {
    }

    public Order(State state, List<OrderItem> items) {
        this.state = state;
        this.items = items;
    }

    public Order(int id, State state, List<OrderItem> items) {
        this.id = id;
        this.state = state;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state=" + state +
                ", items=" + items.toString() +
                '}';
    }
}

enum State {
    CONFIRMED,
    ON_THE_WAY,
    DELIVERED
}