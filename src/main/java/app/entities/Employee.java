package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


public class Employee implements Serializable {

    private Integer employee_id;
    private String login;
    private String password;
    private String fio;
    private Integer auth_lvl;
    private double account;


    public Employee(Integer employee_id,String login, String password , String fio, Integer auth_lvl) {
        this.employee_id = employee_id;
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.auth_lvl = auth_lvl;
    }

    public Employee(Integer employee_id,String login, String password , String fio, Integer auth_lvl, double account) {
        this.employee_id = employee_id;
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.auth_lvl = auth_lvl;
        this.account = account;
    }

    public Employee(String login, String password , String fio, Integer auth_lvl, double account) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.auth_lvl = auth_lvl;
        this.account = account;
    }

    public Employee(String login, String password , String fio, Integer auth_lvl) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.auth_lvl = auth_lvl;
    }

    public Employee(String login, String password, Integer auth_lvl ) {
        this.login = login;
        this.password = password;
        this.auth_lvl = auth_lvl;
    }

    public Employee(String login, String password ) {
        this.login = login;
        this.password = password;
    }

    public Employee() {
    }

    public boolean VerifyUser(String login,String password)
    {
        return login.equals(this.getLogin()) && password.equals(this.getPassword());
    }

    public void reduceBalance(double sum){
        this.account-=sum;
    }

    public void replenishBalance(double sum) {
        this.account+=sum;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getAuth_lvl() {
        return auth_lvl;
    }

    public void setAuth_lvl(Integer auth_lvl) {
        this.auth_lvl = auth_lvl;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }


}
