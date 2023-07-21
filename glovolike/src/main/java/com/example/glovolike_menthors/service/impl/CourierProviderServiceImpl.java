package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Courier;
import com.example.glovolike_menthors.service.AbstractCourierServiceFactory;
import com.example.glovolike_menthors.service.CourierProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CourierProviderServiceImpl implements CourierProviderService {
    private final AbstractCourierServiceFactory courierServiceFactory;

    @Autowired
    public CourierProviderServiceImpl(AbstractCourierServiceFactory courierServiceFactory) {
        this.courierServiceFactory = courierServiceFactory;
    }

    @Override
    public Courier getCourierForDelivery(Coords shopPlacement, Coords clientPlacement) throws NoSuchElementException {
        return courierServiceFactory
                .getCourierServiceByDistanceBetweenShopAndClient(
                        shopPlacement,
                        clientPlacement
                ).getCourierForDelivery(shopPlacement, clientPlacement);
    }
}
