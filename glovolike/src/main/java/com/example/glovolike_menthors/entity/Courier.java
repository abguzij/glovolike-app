package com.example.glovolike_menthors.entity;

public class Courier {
    private Integer id;
    private String name;
    private String surname;
    private Integer speed;
    private Coords myCoords;
    private CourierType courierType;

    public Courier() {
    }

    public Integer getId() {
        return id;
    }

    public Courier setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Courier setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Courier setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Courier setSpeed(Integer speed) {
        this.speed = speed;
        return this;
    }

    public Coords getMyCoords() {
        return myCoords;
    }

    public Courier setMyCoords(Coords myCoords) {
        this.myCoords = myCoords;
        return this;
    }

    public String getFullName() {
        return this.surname + " " + this.name;
    }

    public CourierType getCourierType() {
        return courierType;
    }

    public Courier setCourierType(CourierType courierType) {
        this.courierType = courierType;
        return this;
    }

    // TODO Перенести в сервис
    public Double calculateDeliveryTime(Coords shopCoords, Coords clientCoords) {
        Integer difX1 = Math.abs(myCoords.getCoordX() - shopCoords.getCoordX());
        Integer difY1 = Math.abs(myCoords.getCoordY() - shopCoords.getCoordY());

        Integer difX2 = Math.abs(shopCoords.getCoordX() - clientCoords.getCoordX());
        Integer difY2 = Math.abs(shopCoords.getCoordY() - clientCoords.getCoordY());

        Integer distance = difX1 + difX2 + difY1 + difY2;
        return (double) distance / this.speed;
    }

    public void updateMyCoords(Coords newCoords) {
        this.setMyCoords(newCoords);
    }
}
