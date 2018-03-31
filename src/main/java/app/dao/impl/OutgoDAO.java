package app.dao.impl;

import app.dao.BaseDAO;
import app.dao.IOutgoDAO;
import app.entities.Outgo;
import app.entities.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutgoDAO implements IOutgoDAO {
    BaseDAO dao;
    public OutgoDAO(){

    }

    public OutgoDAO(BaseDAO dao) {
        this.dao = dao;
    }

    public void addOutgo(Outgo outgo) {
        String query = "INSERT INTO outgo(" +
                "task_id, emp_id,summ) VALUES(?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, outgo.getTask_id());
            ps.setInt(2, outgo.getEmp_id());
            ps.setDouble(3, outgo.getSumm());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateOutgo(Outgo outgo) {
        String query = "update outgo set task_id = ?, emp_id = ?, summ = ?" +
                        "  where outgo_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, outgo.getTask_id());
            ps.setInt(2, outgo.getEmp_id());
            ps.setDouble(3, outgo.getSumm());
            ps.setInt(4, outgo.getOutgo_id());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteOutgo(int outgo_id){
       String query = "Delete from outgo where outgo_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, outgo_id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Outgo getOutgoById(int outgo_id)  {
        Outgo res= new Outgo();
        String query = "Select * from outgo where outgo_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,outgo_id);
            ResultSet rs = ps.executeQuery();
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
        String query = "SELECT o.outgo_id,t.task_id,t.description,o.summ " +
                "FROM outgo o,task t " +
                "WHERE o.task_id=t.task_id and o.emp_id=? " +
                "ORDER BY o.outgo_id ASC";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,emp_id);
            ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Outgo outgo = new Outgo();
            outgo.setOutgo_id(rs.getInt("outgo_id"));
            outgo.setEmp_id(emp_id);
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

    //Идет с категории нижнего уровня до родителя, то есть у которого ptask_id==null
    public List<Integer> getParentsIds(int task_id)  {
        List<Integer> res = new ArrayList<>();
        ResultSet rs = dao.execSQL(String.format("WITH RECURSIVE r AS (" +
                " SELECT task_id, ptask_id, description" +
                " FROM task" +
                " WHERE task_id = '%1$s'"+
                " UNION ALL" +
                " SELECT task.task_id, task.ptask_id, task.description" +
                " FROM task" +
                " JOIN r" +
                " ON task.task_id = r.ptask_id)" +
                " SELECT * FROM r",task_id));
        try{
            while (rs.next()){
                res.add(task_id);

        }}catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
    }


    //Сумма затрат на задачу по айдишнику (причем, если таких задач несколько, то считает всю сумму на них)
    public double getSumByTaskId(int task_id,int emp_id){
        String query = "Select sum(summ) from outgo where task_id = ? and emp_id = ?";
        double res=0;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,task_id);
            ps.setInt(2,emp_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               res= rs.getDouble("sum");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
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
