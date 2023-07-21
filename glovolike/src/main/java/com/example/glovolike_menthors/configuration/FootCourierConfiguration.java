package com.example.glovolike_menthors.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/courier.properties"})
public class FootCourierConfiguration {
    @Value(value = "${courier.speed}")
    private Integer courierSpeed;

    public Integer getCourierSpeed() {
        return courierSpeed;
    }
}
