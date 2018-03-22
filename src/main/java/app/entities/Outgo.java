package app.entities;

import java.io.Serializable;


public class Outgo implements Serializable {

    private Integer outgo_id;
    private Task task_id;
//    private Employee emp_id;
    private Integer emp_id;
    private double summ;

    public Outgo(Task task_id, Integer emp_id, Integer summ) {
        this.task_id = task_id;
        this.emp_id = emp_id;
        this.summ = summ;
    }

    public Outgo()    {
    }

    public Integer getOutgo_id() {
        return outgo_id;
    }

    public void setOutgo_id( Integer outgo_id) {
        this.outgo_id = outgo_id;
    }

    public Task getTask_id() {
        return task_id;
    }

    public void setTask_id(Task task_id) {
        this.task_id = task_id;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm( double summ) {
        this.summ = summ;
    }
}
