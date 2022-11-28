package com.ensa.pizzahub.model;

import java.util.List;

public class Order {
    private int id;
    private State state;
    private List<OrderItem> items;
    private User user;

    public Order() {
    }

    public Order(State state, List<OrderItem> items, User user) {
        this.state = state;
        this.items = items;
        this.user = user;
    }

    public Order(int id, State state, List<OrderItem> items, User user) {
        this.id = id;
        this.state = state;
        this.items = items;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

enum State {
    CONFIRMED,
    ON_THE_WAY,
    DELIVERED
}