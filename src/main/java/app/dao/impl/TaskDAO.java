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

    public Map<Integer,String> getNextLvl(Integer task_id){
        Map<Integer,String> res = new HashMap<>();
        ResultSet rs = dao.execSQL(String.format("Select task_id,description from task where ptask_id = '%1$s'",task_id));
        try {
            while (rs.next())
            {
                    res.put(rs.getInt("task_id"),rs.getString("description"));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Task> getAll() {
        List<Task> taskList = new ArrayList<>();
        ResultSet rs = dao.execSQL("select * from task ORDER BY task_id ASC");
        Task task;
        try {
            while (rs.next()){
                if (rs.getInt("ptask_id")==0) {
                    task = new Task(rs.getInt("task_id"), 0, rs.getString("description"));
                }
                else{

                    task = new Task(rs.getInt("task_id"),rs.getInt("ptask_id") , rs.getString("description"));
                }
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public List<String> getParents(){
        List<String> descrs = new ArrayList<String>();
        ResultSet rs = dao.execSQL("Select description from task where ptask_id IS NULL");
        if (addDescription(descrs, rs)) return descrs;
        return null;
    }

    @Override
    public Map<Integer,String> getDescriptionByTaskId(Integer task_id) {
        Map<Integer,String> res = new HashMap<>();
        ResultSet rs = dao.execSQL(String.format("WITH RECURSIVE r AS (" +
                " SELECT task_id, ptask_id, description, 1 AS level" +
                " FROM task" +
                " WHERE task_id = '%1$s'"+
                " UNION ALL" +
                " SELECT task.task_id, task.ptask_id, task.description, r.level + 1 AS level" +
                " FROM task" +
                " JOIN r" +
                " ON task.ptask_id = r.task_id)" +
                " SELECT * FROM r",task_id));
        try {
            while (rs.next())
            {
                if(rs.getInt("level")!=1){
                    res.put(rs.getInt("task_id"),rs.getString("description"));
                }

            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }

    //По айдишнику родителя, находит у него родителей и тд
    public Task getTasksTree(Integer id) throws SQLException {
        ResultSet rs = dao.execSQL(String.format("select task_id,ptask_id,description from task where task_id='%1$s'", id));
        Task task = null;
        while (rs.next()) {
            if (rs.getInt("ptask_id") != 0)
                getTasksTree(rs.getInt("ptask_id"));
            else task = new Task(rs.getInt("task_id"), rs.getInt("ptask_id"), rs.getString("description"));
        }
        return task;
    }

}
