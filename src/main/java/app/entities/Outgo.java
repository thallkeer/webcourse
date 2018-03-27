package app.entities;

import java.io.Serializable;


public class Outgo {

    private int outgo_id;
    private int task_id;
    private String task_description;
//    private Employee emp_id;
    private int emp_id;
    private double summ;

    public Outgo(int task_id, int emp_id, double summ) {
        this.task_id = task_id;
        this.emp_id = emp_id;
        this.summ = summ;
    }

    public Outgo()    {
    }

    public Integer getOutgo_id() {
        return outgo_id;
    }

    public void setOutgo_id( int outgo_id) {
        this.outgo_id = outgo_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm( double summ) {
        this.summ = summ;
    }
    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }
}
