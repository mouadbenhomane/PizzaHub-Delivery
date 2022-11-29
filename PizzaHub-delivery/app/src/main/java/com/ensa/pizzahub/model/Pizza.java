package com.ensa.pizzahub.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pizza implements Parcelable {
    private int id;
    private String description;
    private String name;
    private double price_s;
    private double price_m;
    private double price_l;
    private double deliveryTime;
    private String imagePath;

    public Pizza() {
    }

    public Pizza(String name,String description, double price_s, double price_m, double price_l, double deliveryTime, String imagePath) {
        this.description = description;
        this.name = name;
        this.price_s = price_s;
        this.price_m = price_m;
        this.price_l = price_l;
        this.deliveryTime = deliveryTime;
        this.imagePath = imagePath;
    }

    public Pizza(int id, String name, String description, double price_s, double price_m, double price_l, double deliveryTime, String imagePath) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price_s = price_s;
        this.price_m = price_m;
        this.price_l = price_l;
        this.deliveryTime = deliveryTime;
        this.imagePath = imagePath;
    }

    protected Pizza(Parcel in) {
        id = in.readInt();
        description = in.readString();
        name = in.readString();
        price_s = in.readDouble();
        price_m = in.readDouble();
        price_l = in.readDouble();
        deliveryTime = in.readDouble();
        imagePath = in.readString();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    public double getPrice_s() {
        return price_s;
    }

    public void setPrice_s(double price_s) {
        this.price_s = price_s;
    }

    public double getPrice_m() {
        return price_m;
    }

    public void setPrice_m(double price_m) {
        this.price_m = price_m;
    }

    public double getPrice_l() {
        return price_l;
    }

    public void setPrice_l(double price_l) {
        this.price_l = price_l;
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

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", price_s=" + price_s +
                ", price_m=" + price_m +
                ", price_l=" + price_l +
                ", deliveryTime=" + deliveryTime +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(description);
        parcel.writeString(name);
        parcel.writeDouble(price_s);
        parcel.writeDouble(price_m);
        parcel.writeDouble(price_l);
        parcel.writeDouble(deliveryTime);
        parcel.writeString(imagePath);
    }
}

