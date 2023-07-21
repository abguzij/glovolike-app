package com.example.glovolike_menthors.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConditionalOnProperty(prefix = "courier", name = "bike.enabled", havingValue = "true")
@Configuration
@PropertySource(value = {"classpath:courier.properties"})
public class BikeCourierConfiguration {
    @Value(value = "${courier.bike.speed}")
    private Integer speed;
    @Value(value = "${courier.bike.distance.min}")
    private Integer minDistanceToUseThisCourierType;

    public Integer getSpeed() {
        return speed;
    }

    public Integer getMinDistanceToUseThisCourierType() {
        return minDistanceToUseThisCourierType;
    }
}
