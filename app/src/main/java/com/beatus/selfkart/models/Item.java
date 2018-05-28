package com.beatus.selfkart.models;

/**
 * Created by harikrupa on 20-03-2018.
 */

import java.util.List;

/**
 * Created by ravi on 26/09/17.
 */

public class Item {
    int id;
    String name;
    String description;
    double price;
    int count;
    String thumbnail;
    List<String> offers;

    public Item() {
    }

    public Item(int id, String name, String description, double price, int count, List<String> offers) {
        this.id= id;
        this.name = name;
        this.description= description;
        this.price = price;
        this.count = count;
        this.offers = offers;
    }

    public List<String> getOffers() {
        return offers;
    }

    public void setOffers(List<String> offers) {
        this.offers = offers;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}