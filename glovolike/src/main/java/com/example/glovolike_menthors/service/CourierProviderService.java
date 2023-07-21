package com.example.glovolike_menthors.service;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Courier;

import java.util.NoSuchElementException;

public interface CourierProviderService {
    Courier getCourierForDelivery(Coords shopPlacement, Coords clientPlacement) throws NoSuchElementException;
}
