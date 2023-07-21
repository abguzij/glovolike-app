package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.configuration.BikeCourierConfiguration;
import com.example.glovolike_menthors.configuration.OfficeConfiguration;
import com.example.glovolike_menthors.entity.CourierType;
import com.example.glovolike_menthors.service.AbstractCourierService;
import com.example.glovolike_menthors.service.ShopService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@ConditionalOnBean(BikeCourierConfiguration.class)
@Service
public class BikeCourierService extends AbstractCourierService {
    private final BikeCourierConfiguration bikeCourierConfiguration;
    public BikeCourierService(OfficeConfiguration officeConfiguration,
                              ShopService shopService,
                              BikeCourierConfiguration bikeCourierConfiguration) {
        super(officeConfiguration, shopService);
        this.bikeCourierConfiguration = bikeCourierConfiguration;
    }

    @Override
    public void registerNewCourier(String name, String surname) {
        this.addCourierToTheList(
                this.getCourierTemplate(name, surname)
                        .setCourierType(CourierType.BIKE)
                        .setSpeed(bikeCourierConfiguration.getSpeed())
        );
    }

    @Override
    public Integer getMinDistanceForThisCourierType() {
        return bikeCourierConfiguration.getMinDistanceToUseThisCourierType();
    }
}
