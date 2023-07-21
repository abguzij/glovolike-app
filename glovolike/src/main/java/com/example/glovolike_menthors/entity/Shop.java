package com.example.glovolike_menthors.entity;

import java.util.List;

public class Shop {
    private Integer id;
    private String title;
    private Coords coords;
    private List<Product> products;

    public Shop() {
    }

    public Integer getId() {
        return id;
    }

    public Shop setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Shop setTitle(String title) {
        this.title = title;
        return this;
    }

    public Coords getCoords() {
        return coords;
    }

    public Shop setCoords(Coords coords) {
        this.coords = coords;
        return this;
    }

    public Shop setProducts(List<Product> products) {
        this.products = products;
        return this;
    }


}
