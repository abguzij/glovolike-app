package com.example.glovolike_menthors.service;

import com.example.glovolike_menthors.entity.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public interface ProductService {
    Product registerProduct(String title, Integer price) throws IllegalArgumentException;
    Product getProductById(Integer id) throws NoSuchElementException;
    List<Product> getProductsListByIds(Set<Integer> productsIds) throws NoSuchElementException;
    Integer getProductIdByTitleAndPrice(String title, Integer price);
}
