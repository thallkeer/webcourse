package app.dao.impl;

import app.dao.IOutgoDAO;
import app.entities.Outgo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
                        "outgo_id, task_id, emp_id,summ) values('%1$s','%2$s','%3$s','%4$s')",
                outgo.getOutgo_id(),
                outgo.getTask_id(),
                outgo.getEmp_id(),
                outgo.getSumm()));
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
