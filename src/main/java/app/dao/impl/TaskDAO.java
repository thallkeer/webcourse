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

    public void addProject(Task project){
        ResultSet rs = dao.execSQL("Select max(task_id) from task");
        int id=0;
        try {
            while (rs.next())
            id  = rs.getInt("max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id>0) {
            dao.execute(String.format("INSERT INTO task(" +
                            "task_id, ptask_id, description, org_name, isarchival) values('%1$s',null,'%2$s','%3$s','%4$s')",
                    id+1,
                    project.getDescription(),
                    project.getOrganization(),
                    project.isArchival()));
        }
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

    public Map<Integer,String> getParents(){
        Map<Integer,String> descrs = new HashMap<>();
        ResultSet rs = dao.execSQL("Select task_id,description from task where ptask_id IS NULL");
        try {
            while (rs.next()) {
                descrs.put(rs.getInt("task_id"),rs.getString("description"));
            }
            return descrs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Task getTaskById(Integer task_id) throws SQLException {
        Task res = new Task();
        ResultSet rs = dao.execSQL(String.format("select * from task where task_id='%1$s'",task_id));
        while (rs.next()){
            res.setTask_id(rs.getInt(1));
            res.setPtask_id(rs.getInt(2));
            res.setDescription(rs.getString(3));
            res.setOrganization(rs.getString(4));
            res.setArchival(rs.getBoolean(5));
        }
        return res;
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

    //Находит родителя самого нижнего уровня
    public Task getTasksTree(Integer id) throws SQLException {
        ResultSet rs = dao.execSQL(String.format("select task_id,ptask_id,description from task where task_id='%1$s'", id));
        Task task = null;
        while (rs.next()) {
            if (rs.getInt("ptask_id") == 0)
                return new Task(rs.getInt("task_id"), rs.getInt("ptask_id"), rs.getString("description"));
            task = getTasksTree(rs.getInt("ptask_id"));
        }
       return task;
    }


}
