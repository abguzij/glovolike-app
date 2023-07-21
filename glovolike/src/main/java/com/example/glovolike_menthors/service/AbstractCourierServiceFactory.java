package com.example.glovolike_menthors.service;

import com.example.glovolike_menthors.entity.Coords;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractCourierServiceFactory {
    private final List<CourierService> courierServiceList;

    private Map<Integer, CourierService> courierServiceMap;
    private List<Integer> minDistanceValues;

    @Autowired
    protected AbstractCourierServiceFactory(List<CourierService> courierServiceList) {
        this.courierServiceList = courierServiceList;
        this.courierServiceMap = new HashMap<>();
        this.minDistanceValues = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        initializeCourierServiceMap();
        initializeMinDistanceValuesList();
        sortMinDistanceValuesInDescendingOrder();
    }

//    protected List<CourierService> getCourierServiceList(){
//        return this.courierServiceList;
//    }

    protected List<Integer> getMinDistanceValuesList(){
        return this.minDistanceValues;
    }

    protected CourierService getCourierServiceByMinDistanceValue(Integer minDistanceValue){
        return this.courierServiceMap.get(minDistanceValue);
    }

    public abstract CourierService getCourierServiceByDistanceBetweenShopAndClient(
            Coords shopPlacement,
            Coords clientPlacement
    ) throws NoSuchElementException;

    private void initializeCourierServiceMap(){
        for (CourierService courierService:
             this.courierServiceList) {
            this.courierServiceMap.put(
                    courierService.getMinDistanceForThisCourierType(),
                    courierService
            );
        }
    }

    private void initializeMinDistanceValuesList(){
        for (CourierService courierService:
             this.courierServiceList) {
            this.minDistanceValues.add(courierService.getMinDistanceForThisCourierType());
        }
    }

    private void sortMinDistanceValuesInDescendingOrder(){
        this.minDistanceValues = this.minDistanceValues
                .stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }
}
