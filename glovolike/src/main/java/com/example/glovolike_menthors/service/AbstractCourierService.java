package com.example.glovolike_menthors.service;

import com.example.glovolike_menthors.configuration.OfficeConfiguration;
import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Courier;
import com.example.glovolike_menthors.utils.CourierBuildingUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

public abstract class AbstractCourierService implements CourierService {
    private List<Courier> couriers;
    private Coords officeCoords;

    //private final FootCourierConfiguration courierConfiguration; // Проблема, что другим курьерам не нужна этот DI
    private final OfficeConfiguration officeConfiguration;
    private final ShopService shopService;

    @Autowired
    public AbstractCourierService(OfficeConfiguration officeConfiguration, ShopService shopService) {
        //this.courierConfiguration = courierConfiguration;
        this.officeConfiguration = officeConfiguration;
        this.shopService = shopService;
    }

    @PostConstruct
    private void init() {
        this.couriers = new ArrayList<>();
        this.officeCoords = new Coords()
                .setCoordX(officeConfiguration.getCoordX())
                .setCoordY(officeConfiguration.getCoordY());
    }

    public abstract void registerNewCourier(String name, String surname);
    public abstract Integer getMinDistanceForThisCourierType();
    @Override
    public Courier getCourierForDelivery(Coords shopCoords, Coords clientCoords) throws NoSuchElementException {
        if (this.couriers.isEmpty()) {
            throw new NoSuchElementException("В системе не было зарегистрировано ни одного курьера заданного типа!");
        }
        Courier courierForDelivery = this.couriers.get(0);
        Double courierTime = courierForDelivery.calculateDeliveryTime(shopCoords, clientCoords);
        for (Courier courier : this.couriers) {
            if (courier.calculateDeliveryTime(shopCoords, clientCoords) < courierTime) {
                courierTime = courier.calculateDeliveryTime(shopCoords, clientCoords);
                courierForDelivery = courier;
            }
        }
        return courierForDelivery;
    }

    protected void addCourierToTheList(Courier courier){
        this.couriers.add(courier);
    }

    protected Courier getCourierTemplate(String name, String surname){
        return CourierBuildingUtils.generateCourierTemplate(
                this.couriers.size(),
                name,
                surname,
                this.officeCoords
        );
    }
}
