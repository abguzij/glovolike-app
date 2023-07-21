package com.example.glovolike_menthors.service;

import java.util.NoSuchElementException;
import java.util.Set;

public interface OrderService {
    void sellOneProduct(Integer shopId, Integer productId);
    void  sellProductOfValue(Integer shopId, Integer productId, Integer value) throws IllegalArgumentException;
    void registerShop(Integer shopId) throws IllegalArgumentException;
    void registerProduct(Integer shopId, Integer productId) throws IllegalArgumentException;
    void updateProductCount(Integer shopId, Integer productId, Integer count) throws NoSuchElementException;
    Integer getProductCount(Integer shopId, Integer productId) throws NoSuchElementException;
    Set<Integer> getIdsOfProductsInShop(Integer shopId) throws NoSuchElementException;
}
