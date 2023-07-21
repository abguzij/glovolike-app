package com.example.glovolike_menthors.utils;

import com.example.glovolike_menthors.entity.Coords;

public abstract class DistanceEvaluationUtils {
    public static Integer evaluateDistanceBetweenPoints(Coords startPoint, Coords endPoint){
        return Math.abs(startPoint.getCoordX() - endPoint.getCoordX()) +
        Math.abs(startPoint.getCoordY() - endPoint.getCoordY());
    }
}
