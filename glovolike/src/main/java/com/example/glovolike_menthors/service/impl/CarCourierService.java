package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.configuration.CarCourierConfiguration;
import com.example.glovolike_menthors.configuration.OfficeConfiguration;
import com.example.glovolike_menthors.entity.CourierType;
import com.example.glovolike_menthors.service.AbstractCourierService;
import com.example.glovolike_menthors.service.ShopService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@ConditionalOnBean(CarCourierConfiguration.class)
@Service
public class CarCourierService extends AbstractCourierService {
    private final CarCourierConfiguration courierConfiguration;
    public CarCourierService(OfficeConfiguration officeConfiguration,
                             ShopService shopService,
                             CarCourierConfiguration courierConfiguration) {
        super(officeConfiguration, shopService);
        this.courierConfiguration = courierConfiguration;
    }

    @Override
    public void registerNewCourier(String name, String surname) {
        this.addCourierToTheList(
                this.getCourierTemplate(name, surname)
                        .setCourierType(CourierType.CAR)
                        .setSpeed(courierConfiguration.getSpeed())
        );
    }

    @Override
    public Integer getMinDistanceForThisCourierType() {
        return courierConfiguration.getMinDistanceToUseThisCourierType();
    }
}
