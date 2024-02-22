package com.example.pharmaapp.Models;

import android.widget.ImageView;

import java.io.Serializable;

public class ProductsModel implements Serializable {
    public ProductsModel(int img_url,  String description, String name, String rating, String price, String webUrl) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img_url = img_url;
        this.webUrl = webUrl;
    }

    String description, name, rating, price;
    int img_url;
    private String webUrl;


    public String getWebUrl() {
        return webUrl;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductsModel(){
    }



}
