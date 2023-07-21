package com.example.glovolike_menthors.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/office.properties")
public class OfficeConfiguration {
    @Value(value = "${coord.x}")
    private Integer coordX;

    @Value(value = "${coord.y}")
    private Integer coordY;

    public Integer getCoordX() {
        return coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }
}
