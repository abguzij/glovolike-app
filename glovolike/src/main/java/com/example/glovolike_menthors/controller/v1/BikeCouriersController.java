package com.example.glovolike_menthors.controller.v1;

import com.example.glovolike_menthors.configuration.BikeCourierConfiguration;
import com.example.glovolike_menthors.service.impl.BikeCourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnBean(BikeCourierConfiguration.class)
@RestController
@RequestMapping(value = "/couriers/bike")
@Tag(name = "Bike Couriers Controller",
        description = "End-pont'ы, предоставляемые клиентскому приложению " +
                "для регистрации вело-курьеров")
public class BikeCouriersController {
    private final BikeCourierService bikeCourierService;

    public BikeCouriersController(BikeCourierService bikeCourierService) {
        this.bikeCourierService = bikeCourierService;
    }

    @Operation(summary = "Добавление нового вело-курьера с именем name и фамилией surname",
            description = "Добавляет нового вело-курьера. Возможно зарегистрировать несколько курьеров " +
                    "с одинаковым именем и фамилией")
    @PostMapping(value = "/new")
    public String registerNewBikeCourier(String name, String surname){
        this.bikeCourierService.registerNewCourier(name, surname);
        return "OK";
    }
}
