package com.example.glovolike_menthors.entity;

public class Coords {
    private Integer coordX;
    private Integer coordY;

    public Coords() {
    }

    public Coords(Integer coordX, Integer coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public Coords setCoordX(Integer coordX) {
        this.coordX = coordX;
        return this;
    }

    public Integer getCoordY() {
        return coordY;
    }

    public Coords setCoordY(Integer coordY) {
        this.coordY = coordY;
        return this;
    }
}
