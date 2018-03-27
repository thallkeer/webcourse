package app.entities;

import java.io.Serializable;

public class Auto {

    private int auto_id;

    private String brand;

    private double consumption; //норма расхода литров на 100км

    public Auto(int auto_id, String brand, double consumption) {
        this.auto_id = auto_id;
        this.brand = brand;
        this.consumption = consumption;
    }

    public Integer getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(final int auto_id) {
        this.auto_id = auto_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }


    /**
     * @param benzprice цена за литр бензина
     * @param distance расстояние, которое проехал автомобиль
     * @return
     */
    public double getsum(double benzprice,double distance){
        return benzprice*this.consumption*distance/100;
    }

}
