package com.example.glovolike_menthors.service;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Shop;

import java.util.NoSuchElementException;
import java.util.Set;

public interface ShopService {
    Shop getShopById(Integer id) throws NoSuchElementException;
    void addNewShop(String title, Integer coordX, Integer coordY) throws IllegalArgumentException;// Чтобы было удобно парсить
    void addNewProduct(Integer shopId, String title, Integer price) throws NoSuchElementException;
    Coords getPlacement(Integer shopId) throws NoSuchElementException;
    Set<Shop> getAllShops() throws NoSuchElementException;
}
