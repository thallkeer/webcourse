package app.entities;

import java.io.Serializable;

public class Auto implements Serializable {

    private Integer auto_id;

    private String brand;

    private Integer consumption;

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
}
