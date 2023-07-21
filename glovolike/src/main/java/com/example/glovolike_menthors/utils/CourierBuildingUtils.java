package com.example.glovolike_menthors.utils;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Courier;

public abstract class CourierBuildingUtils {
    public static Courier generateCourierTemplate(Integer id, String name, String surname, Coords courierCoords){
        return new Courier()
                .setId(id)
                .setName(name)
                .setSurname(surname)
                .setMyCoords(courierCoords);
    }
}
