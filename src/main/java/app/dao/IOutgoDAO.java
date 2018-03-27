package app.dao;

import app.entities.Outgo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOutgoDAO {
    public void addOutgo(Outgo outgo);
    public List<Outgo> getOutgoesByEmpId(Integer emp_id);
    public void getSum();
    public Map<String,Double> getPtaskSum(Integer emp_id,Integer ptask_id);
    public Outgo getOutgoById(int outgo_id);
    public void deleteOutgo(int outgo_id);
    public void updateOutgo(Outgo outgo);

}
