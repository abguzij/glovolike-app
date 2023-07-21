package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.service.AbstractCourierServiceFactory;
import com.example.glovolike_menthors.service.CourierService;
import com.example.glovolike_menthors.utils.DistanceEvaluationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourierServiceFactoryImpl extends AbstractCourierServiceFactory {
    protected CourierServiceFactoryImpl(List<CourierService> courierServiceList) {
        super(courierServiceList);
    }

    @Override
    public CourierService getCourierServiceByDistanceBetweenShopAndClient(
            Coords shopPlacement,
            Coords clientPlacement
    ) throws NoSuchElementException
    {
        Integer distance = DistanceEvaluationUtils.evaluateDistanceBetweenPoints(shopPlacement, clientPlacement);
        for (Integer minDistanceValue:
                this.getMinDistanceValuesList()) {
            if(distance > minDistanceValue){
                return this.getCourierServiceByMinDistanceValue(minDistanceValue);
            }
        }
        throw new NoSuchElementException("В системе не было зарегистрировано ни одного" +
                " сервиса для работы с курьерами!");
    }
}
