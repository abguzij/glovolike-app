package com.example.glovolike_menthors.controller.v1;

import com.example.glovolike_menthors.configuration.CarCourierConfiguration;
import com.example.glovolike_menthors.service.impl.CarCourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnBean(CarCourierConfiguration.class)
@RestController
@RequestMapping(value = "/couriers/car")
@Tag(name = "Car Couriers Controller",
        description = "End-pont'ы, предоставляемые клиентскому приложению " +
                "для регистрации авто-курьеров")
public class CarCouriersController {
    private final CarCourierService carCourierService;

    public CarCouriersController(CarCourierService carCourierService) {
        this.carCourierService = carCourierService;
    }

    @Operation(summary = "Добавление нового авто-курьера с именем name и фамилией surname",
            description = "Добавляет нового авто-курьера. Возможно зарегистрировать несколько курьеров " +
                    "с одинаковым именем и фамилией")
    @PostMapping(value = "/new")
    public String registerNewCarCourier(String name, String surname){
        carCourierService.registerNewCourier(name, surname);
        return "OK";
    }
}
