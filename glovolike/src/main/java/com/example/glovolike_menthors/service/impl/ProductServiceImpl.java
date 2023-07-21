package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.entity.Product;
import com.example.glovolike_menthors.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
    }

    @Override
    public Product registerProduct(String title, Integer price) throws IllegalArgumentException {
        Optional<Product> possibleDuplicate = this.products
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .findFirst();
        if (possibleDuplicate.isPresent()){
            throw new IllegalArgumentException("Такой товар уже зарегистрирован в системе!");
        }

        Product product = new Product()
                .setId(this.products.size())
                .setTitle(title)
                .setPrice(price);

        this.products.add(product);
        return product;
    }

    @Override
    public Product getProductById(Integer id) throws NoSuchElementException {
        Product product = this.products.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if(Objects.nonNull(product)){
            return this.products.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        }
        throw new NoSuchElementException("Продукта с таким ID не зарегистрировано в системе");
    }

    @Override
    public List<Product> getProductsListByIds(Set<Integer> productsIds) throws NoSuchElementException {
        List<Product> productList = productsIds
                .stream()
                .map(id -> this.products.get(id))
                .collect(Collectors.toList());
        if(productList.size() == 0){
            throw new NoSuchElementException("На складе магазина нет еще ни одного товара!");
        }
        return productList;
    }

    @Override
    public Integer getProductIdByTitleAndPrice(String title, Integer price) {
        Product product = this.products
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .filter(x -> x.getPrice().equals(price))
                .findFirst().orElse(null);
        if(Objects.nonNull(product)){
            return product.getId();
        }
        return null;
    }
}
