package com.ensa.pizzahub.model;

public class Pizza {
    private int id;
    private String description;
    private String name;
    private double price;
    private double deliveryTime;
    private String imagePath;
    private ItemSize size;

    public Pizza() {
    }

    public Pizza(String description, String name, double price, double deliveryTime, String imagePath, ItemSize size) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.imagePath = imagePath;
        this.size = size;
    }

    public Pizza(int id, String description, String name, double price, double deliveryTime, String imagePath, ItemSize size) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.imagePath = imagePath;
        this.size = size;
    }
    public ItemSize getSize() {
        return size;
    }

    public void setSize(ItemSize size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

enum ItemSize{
    SMALL,
    MEDIUM,
    LARGE
}
