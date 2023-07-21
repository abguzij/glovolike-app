package com.example.glovolike_menthors.service;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Courier;

import java.util.NoSuchElementException;

public interface CourierService {
    void registerNewCourier(String name, String surname);
    Courier getCourierForDelivery(Coords shopCoords, Coords clientCoords) throws NoSuchElementException;
    Integer getMinDistanceForThisCourierType();

}
