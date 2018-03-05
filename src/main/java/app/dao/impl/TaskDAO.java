package app.dao.impl;

import app.dao.ITaskDAO;
import app.entities.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if (CreateAndReturn(tasks, resultSet)) return tasks;
        return null;
    }

    private boolean CreateAndReturn(List<Task> tasks, ResultSet resultSet) {
        try {
            while (resultSet.next())
            {
               Task tmptask = new Task();
               tmptask.setTask_id(resultSet.getInt("task_id"));
               tmptask.setDescription(resultSet.getString("description"));
               tasks.add(tmptask);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Task> getTasksByLvl(Integer lvl) {
        return null;
    }



    @Override
    public List<Task> getTasksByParentId(Integer parent_id) {
        List<Task> tasks = new ArrayList<Task>();
        ResultSet resultSet = dao.execSQL(String.format("SELECT t.description,summ FROM outgo o " +
                "left join (select employee_id,login from employee) as e on e.employee_id=o.emp_id " +
                "left join (select task_id,ptask_id,description from task) as t on t.task_id=o.task_id where ptask_id='%1$s'",parent_id));
        if (CreateAndReturn(tasks, resultSet)) return tasks;
        return null;
    }

    @Override
    public List<String> getTaskDescriptionByLvl(Integer lvl) {
        List<String> descrs = new ArrayList<String>();
        ResultSet rs = dao.execSQL(String.format("WITH RECURSIVE r AS (\n" +
                "SELECT task_id, ptask_id, description, 1 AS level\n" +
                "FROM task\n" +
                "WHERE task_id = 1\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT task.task_id, task.ptask_id, task.description, r.level + 1 AS level\n" +
                "FROM task\n" +
                "JOIN r\n" +
                "ON task.ptask_id = r.task_id\n" +
                "  WHERE r.level < '%1$s'\n" +
                ")\n" +
                "\n" +
                "SELECT * FROM r;",lvl));
        try {
            while (rs.next())
            {
                descrs.add( rs.getString("description"));
            }
            return descrs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }

}
