package com.landon.restauranttest;

public class Restaurant {
    private String name;
    PriceRange priceRange;

    public Restaurant(String name, PriceRange priceRange) {
        this.name = name;
        this.priceRange = priceRange;
    }

    protected String getName() {
        return name;
    }

    protected PriceRange getPriceRange() {
        return priceRange;
    }
}
