package app.dao.impl;

import app.dao.IOutgoDAO;
import app.entities.Outgo;
import app.entities.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutgoDAO implements IOutgoDAO {
    PostgresDAO dao;
    public OutgoDAO(){

    }

    public OutgoDAO(PostgresDAO dao) {
        this.dao = dao;
    }

    public void addOutgo(Outgo outgo){
        dao.execute(String.format("INSERT INTO outgo(" +
                        "task_id, emp_id,summ) values('%1$s','%2$s','%3$s')",
                outgo.getTask_id(),
                outgo.getEmp_id(),
                outgo.getSumm()));
    }

    public void updateOutgo(Outgo outgo) {
        dao.execute(String.format("update outgo set task_id = '%1$s', emp_id = '%2$s', summ = '%3$s'" +
                        "  where outgo_id = %4$s",
                outgo.getTask_id(),
                outgo.getEmp_id(),
                outgo.getSumm(),
                outgo.getOutgo_id()));
    }

    public void deleteOutgo(int outgo_id){
        dao.execute(String.format("Delete from outgo where outgo_id = '%1$s'",outgo_id));
    }


    @Override
    public Outgo getOutgoById(int outgo_id)  {
        Outgo res= new Outgo();
        ResultSet rs = dao.execSQL(String.format("Select * from outgo where outgo_id = '%1$s'",outgo_id));
        try {
            while (rs.next()){
                res.setOutgo_id(rs.getInt("outgo_id"));
                res.setTask_id(rs.getInt("task_id"));
                res.setEmp_id(rs.getInt("emp_id"));
                res.setSumm(rs.getDouble("summ"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return res;
    }

    public List<Outgo> getOutgoesByEmpId(Integer emp_id)   {
        List<Outgo> res = new ArrayList<>();
        ResultSet rs = dao.execSQL(String.format("SELECT o.outgo_id,t.task_id,t.description,o.summ " +
                "FROM outgo o,task t " +
                "WHERE o.task_id=t.task_id and o.emp_id='%1$s' " +
                "ORDER BY o.outgo_id ASC",emp_id));
        try{
        while (rs.next()){
            Outgo outgo = new Outgo();
            outgo.setOutgo_id(rs.getInt("outgo_id"));
            outgo.setTask_id(rs.getInt("task_id"));
            outgo.setSumm(rs.getDouble("summ"));
            outgo.setTask_description(rs.getString("description"));
            res.add(outgo);
        }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
    }


    public void getSum()  {
        ResultSet rs = dao.execSQL(String.format("WITH RECURSIVE r AS (" +
                " SELECT task_id, ptask_id, description, 1 AS level" +
                " FROM task" +
                " WHERE task_id = 5"+
                " UNION ALL" +
                " SELECT task.task_id, task.ptask_id, task.description, r.level + 1 AS level" +
                " FROM task" +
                " JOIN r" +
                " ON task.task_id = r.ptask_id)" +
                " SELECT * FROM r"));
    }


    //Возвращает сумму, потраченную работником на затрату уровнем выше
    public Map<String,Double> getPtaskSum(Integer emp_id,Integer ptask_id)  {
        Map<String,Double> res = new HashMap<>();
        ResultSet rs = dao.execSQL(String.format("SELECT t.ptask_id,o.emp_id,t.description,sum(summ) FROM outgo o,task t" +
                " WHERE o.task_id = t.task_id and t.ptask_id='%1$s' and emp_id='%2$s' GROUP BY t.ptask_id,t.description,o.emp_id",ptask_id,emp_id));
        try {
            while (rs.next()) {
                res.put(rs.getString("description"), rs.getDouble("sum"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
    }

}
