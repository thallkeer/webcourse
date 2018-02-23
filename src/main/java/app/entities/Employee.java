package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employee_id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "fio")
    private String fio;

    @Column(name="auth_lvl")
    private Integer auth_lvl;

    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Outgo> outgoes;

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

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(final Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(final String fio) {
        this.fio = fio;
    }


    public Integer getAuth_lvl() {
        return auth_lvl;
    }

    public void setAuth_lvl(final Integer auth_lvl) {
        this.auth_lvl = auth_lvl;
    }
}
