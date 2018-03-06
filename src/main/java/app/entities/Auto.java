package app.entities;

import java.io.Serializable;

public class Auto implements Serializable {

    private Integer auto_id;

    private String brand;

    private Integer consumption; //норма расхода литров на 100км

    public Auto(Integer auto_id, String brand, Integer consumption) {
        this.auto_id = auto_id;
        this.brand = brand;
        this.consumption = consumption;
    }

    public Integer getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(final Integer auto_id) {
        this.auto_id = auto_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }


    /**
     * @param benzprice цена за литр бензина
     * @param distance расстояние, которое проехал автомобиль
     * @return
     */
    public int getsum(int benzprice,int distance){
        return benzprice*this.consumption*distance/100;
    }

}
