package com.example.glovolike_menthors.service.impl;

import com.example.glovolike_menthors.configuration.FootCourierConfiguration;
import com.example.glovolike_menthors.configuration.OfficeConfiguration;
import com.example.glovolike_menthors.entity.CourierType;
import com.example.glovolike_menthors.service.AbstractCourierService;
import com.example.glovolike_menthors.service.ShopService;
import org.springframework.stereotype.Service;

@Service
public class FootCourierService extends AbstractCourierService {
    private final FootCourierConfiguration footCourierConfiguration;
    public FootCourierService(OfficeConfiguration officeConfiguration,
                              ShopService shopService,
                              FootCourierConfiguration footCourierConfiguration) {
        super(officeConfiguration, shopService);
        this.footCourierConfiguration = footCourierConfiguration;
    }

    @Override
    public void registerNewCourier(String name, String surname) {
        this.addCourierToTheList(
                this.getCourierTemplate(name, surname)
                        .setCourierType(CourierType.CAR)
                        .setSpeed(footCourierConfiguration.getCourierSpeed())
        );
    }

    @Override
    public Integer getMinDistanceForThisCourierType() {
        return 0;
    }
}
