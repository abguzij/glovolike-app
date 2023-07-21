package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private Map<Integer, Map<Integer, Integer>> shopRegistry; // Shop_id, product_id, count

    public OrderServiceImpl() {
    }

    @PostConstruct
    private void init(){
        this.shopRegistry = new HashMap<>();
    }

    @Override
    public void sellOneProduct(Integer shopId, Integer productId) {
        Integer productCount = this.shopRegistry.get(shopId).get(productId);
        if(productCount > 0){
            this.shopRegistry.get(shopId).put(productId, productCount - 1); // Обновится значение или добавится в конец
        }
    }

    @Override
    public void sellProductOfValue(Integer shopId, Integer productId, Integer value) throws IllegalArgumentException {
        Integer productCount = this.shopRegistry.get(shopId).get(productId);
        if(value > productCount){
            throw new IllegalArgumentException("Количество товара в заказе не может превышать" +
                    " количество товара в магазине!");
        }
        this.shopRegistry.get(shopId).put(productId, productCount - value);
    }

    @Override
    public void registerShop(Integer shopId) throws IllegalArgumentException {
        if (!this.shopRegistry.containsKey(shopId)) {
            this.shopRegistry.put(shopId, new HashMap<>());
        } else {
            throw new IllegalArgumentException("Магазин с таким ID уже существует!");
        }
    }

    @Override
    public void registerProduct(Integer shopId, Integer productId) throws IllegalArgumentException {
        if(!this.shopRegistry.get(shopId).containsKey(productId)){
            this.shopRegistry.get(shopId).put(productId, 0);
        } else {
            throw new IllegalArgumentException("Товар с таким ID уже есть в этом магазине!");
        }
    }

    @Override
    public void updateProductCount(Integer shopId, Integer productId, Integer count) throws NoSuchElementException {
        if(this.shopRegistry.get(shopId).containsKey(productId)){
            Integer productCount = this.shopRegistry.get(shopId).get(productId);
            this.shopRegistry.get(shopId).put(productId, productCount + count);
        } else {
            throw new NoSuchElementException("В магазине с указанным ID нет продукта с указанным ID!");
        }
    }

    @Override
    public Integer getProductCount(Integer shopId, Integer productId) throws NoSuchElementException {
        if(this.shopRegistry.get(shopId).containsKey(productId)) {
            return this.shopRegistry.get(shopId).get(productId);
        } else {
            throw new NoSuchElementException("В магазине с указанным ID нет продукта с указанным ID!");
        }
    }

    @Override
    public Set<Integer> getIdsOfProductsInShop(Integer shopId) throws NoSuchElementException {
        if(!this.shopRegistry.containsKey(shopId)){
            throw new NoSuchElementException("В системе еще не зарегистрирован магазин с указанным ID!");
        }
        return this.shopRegistry.get(shopId).entrySet()
                .stream()
                .filter(productRegistry -> productRegistry.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
