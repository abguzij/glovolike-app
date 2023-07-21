package com.example.glovolike_menthors.controller.v1;

import com.example.glovolike_menthors.service.impl.FootCourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/couriers/foot")
@Tag(name = "Foot Couriers Controller",
        description = "End-pont'ы, предоставляемые клиентскому приложению " +
                "для регистрации пеших курьеров")
public class FootCouriersController {
    private final FootCourierService footCourierService;

    public FootCouriersController(FootCourierService footCourierService) {
        this.footCourierService = footCourierService;
    }

    @Operation(summary = "Добавление нового пешего курьера с именем name и фамилией surname",
            description = "Добавляет нового пешего курьера. Возможно зарегистрировать несколько курьеров " +
                    "с одинаковым именем и фамилией")
    @PostMapping(value = "/new")
    public String registerNewFootCourier(String name, String surname){
        footCourierService.registerNewCourier(name, surname);
        return "OK";
    }


}
