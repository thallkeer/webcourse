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
                outgo.getTask_id().getTask_id(),
                outgo.getEmp_id(),
                outgo.getSumm()));

    }

    public List<Outgo> getOutgoesByEmpId(Integer emp_id) throws SQLException {
        List<Outgo> res = new ArrayList<>();
        ResultSet rs = dao.execSQL(String.format("SELECT o.outgo_id,t.task_id,t.description,o.summ " +
                "FROM outgo o,task t " +
                "WHERE o.task_id=t.task_id and o.emp_id='%1$s' " +
                "ORDER BY o.outgo_id ASC",emp_id));
        while (rs.next()){
            Outgo outgo = new Outgo();
            outgo.setOutgo_id(rs.getInt("outgo_id"));
            Task tmpTask = new Task();
            tmpTask.setTask_id(rs.getInt("task_id"));
            tmpTask.setDescription(rs.getString("description"));
            outgo.setTask_id(tmpTask);
            outgo.setSumm(rs.getDouble("summ"));
            res.add(outgo);
        }
        return res;
    }


    //Возвращает сумму, потраченную работником на затрату уровнем выше
    public Map<Integer,Integer> getPtaskSum(Integer emp_id,Integer ptask_id) throws SQLException {
        Map<Integer,Integer> res = new HashMap<>();
        ResultSet rs = dao.execSQL(String.format("SELECT t.ptask_id,emp_id,sum(summ) FROM outgo o join task t on o.task_id = t.task_id " +
                "WHERE t.ptask_id='%1$s' and emp_id='%2$s' GROUP BY t.ptask_id,emp_id",ptask_id,emp_id));
        while (rs.next()){
            res.put(rs.getInt("ptask_id"),rs.getInt("sum"));
        }
        return res;
    }

}
