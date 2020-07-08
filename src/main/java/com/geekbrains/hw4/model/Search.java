package com.geekbrains.hw4.model;

import org.springframework.context.annotation.Bean;


public class Search {
    private String maxPrice;
    private String minPrice;

    public Search() {
    }

    public Search(String maxPrice, String minPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }
}
