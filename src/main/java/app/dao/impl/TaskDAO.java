package app.dao.impl;

import app.dao.ITaskDAO;
import app.entities.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskDAO implements ITaskDAO {
    PostgresDAO dao;

    public TaskDAO(){

    }

    public TaskDAO(PostgresDAO dao) {
        this.dao = dao;
    }


    @Override
    public List<Task> getUserTasks(String login) {
        List<Task> tasks = new ArrayList<Task>();
        ResultSet resultSet = dao.execSQL(String.format("SELECT t.task_id,t.ptask_id,t.description,summ FROM outgo o " +
                "left join (select employee_id,login from employee) as e on e.employee_id=o.emp_id " +
                "left join (select task_id,ptask_id,description from task) as t on t.task_id=o.task_id where login='%1$s'",login));
        try {
            while (resultSet.next())
            {
                Task tmptask = new Task();
                tmptask.setDescription(resultSet.getString("description"));
                tmptask.setTask_id(resultSet.getInt("task_id"));
                tasks.add(tmptask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }



    @Override
    public List<Task> getTasksByLvl(Integer lvl) {
        return null;
    }


    @Override
    public List<String> getTasksByParentId(Integer parent_id) {
        return null;
    }

    public List<String> getNextLvl(int ptask_id){
        List<String> descs = new ArrayList<>();
        ResultSet rs = dao.execSQL(String.format("Select description from task where ptask_id = '%1$s'",ptask_id));
        if (addDescription(descs, rs)) return descs;
        return null;
    }

    private boolean addDescription(List<String> descs, ResultSet rs) {
        try {
            while (rs.next()) {
                descs.add(rs.getString("description"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String,String> getDescriptionsForParent(Integer task_id){
        Map<String,String> res = new HashMap<>();
        ResultSet rs = dao.execSQL("Select description from task where ptask_id IS NULL");
        return null;
    }

    public List<String> getParents(){
        List<String> descrs = new ArrayList<String>();
        ResultSet rs = dao.execSQL("Select description from task where ptask_id IS NULL");
        if (addDescription(descrs, rs)) return descrs;
        return null;
    }

    @Override
    public List<String> getDescriptionByIdLvl(Integer task_id,Integer lvl) {
        List<String> descrs = new ArrayList<String>();
        ResultSet rs = dao.execSQL(String.format("WITH RECURSIVE r AS (" +
                " SELECT task_id, ptask_id, description, 1 AS level" +
                " FROM task" +
                " WHERE task_id = '%1$s'"+
                " UNION ALL" +
                " SELECT task.task_id, task.ptask_id, task.description, r.level + 1 AS level" +
                " FROM task" +
                " JOIN r" +
                " ON task.ptask_id = r.task_id" +
                "  WHERE r.level < '%2$s')" +
                " SELECT * FROM r",task_id,lvl));
        try {
            while (rs.next())
            {
                if(rs.getInt("level")!=1)
                descrs.add( rs.getString("description"));
            }
            return descrs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }

}
