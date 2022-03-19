package com.example.springbotcarsapp;

public class Car {

    private long id;
    private String marka;
    private String model;
    private String kolor;

    public Car(long id, String marka, String model, String kolor) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.kolor = kolor;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }
}
