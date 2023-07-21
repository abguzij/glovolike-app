package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Product;
import com.example.glovolike_menthors.entity.Shop;
import com.example.glovolike_menthors.service.OrderService;
import com.example.glovolike_menthors.service.ProductService;
import com.example.glovolike_menthors.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ShopServiceImpl implements ShopService {
    private Set<Shop> shops;
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public ShopServiceImpl(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostConstruct
    public void init(){
        this.shops = new HashSet<>();
    }

    @Override
    public Shop getShopById(Integer id) throws NoSuchElementException {
        Shop shop = this.shops.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if(Objects.nonNull(shop)){
            return shop;
        }
        throw new NoSuchElementException("Магазина с таким ID не существует в системе!");
    }

    @Override
    public void addNewShop(String title, Integer coordX, Integer coordY) throws IllegalArgumentException {
        Shop shop = new Shop().setId(this.shops.size()).setTitle(title).setCoords(new Coords(coordX, coordY));
        this.shops.add(shop);
        this.orderService.registerShop(shop.getId());
    }

    @Override
    public void addNewProduct(Integer shopId, String title, Integer price) throws IllegalArgumentException {
        Optional<Shop> shopOptional = this.shops.stream().filter(x -> x.getId().equals(shopId)).findFirst();
        if(!shopOptional.isPresent()) {
            throw new IllegalArgumentException("Магазина с таким ID не существует в системе!");
        }

        Product product;
        Integer possibleProductDuplicateId = this.productService.getProductIdByTitleAndPrice(title, price);
        if(Objects.nonNull(possibleProductDuplicateId)) {
            product = this.productService.getProductById(possibleProductDuplicateId);
        } else {
            product = this.productService.registerProduct(title, price);
        }
        this.orderService.registerProduct(shopId, product.getId());
    }

    @Override
    public Coords getPlacement(Integer shopId) throws NoSuchElementException {
        Shop shop = this.getShopById(shopId);
        if (Objects.nonNull(shop)){
            return shop.getCoords();
        }
        throw new NoSuchElementException("Магазина с таким ID не существует в системе!");
    }

    @Override
    public Set<Shop> getAllShops() throws NoSuchElementException {
        if (this.shops != null && this.shops.size() > 0) {
            return this.shops;
        }
        throw new NoSuchElementException("В системе еще не зарегистрировано ни одного магазина!");
    }
}
