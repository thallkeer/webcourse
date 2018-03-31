package app.dao.impl;

import app.dao.BaseDAO;
import app.dao.ITaskDAO;
import app.entities.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskDAO implements ITaskDAO {
    private BaseDAO dao;
    public TaskDAO(){

    }

    public TaskDAO(BaseDAO dao) {
        this.dao = dao;
    }


    public void addCategory(Task task) {
        String query = "Insert into task(ptask_id,description) values (?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, task.getPtask_id());
            ps.setString(2,task.getDescription());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addCategory(Task parent, String child) {
        String selectMax = "SELECT max(task_id) FROM task";
        String insertQuery = "Insert into task(task_id,ptask_id,description) values (?,?,?)";
        int parent_id = -1;
        int child_id = 0;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectMax);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                parent_id = rs.getInt("max") + 1;
            }
            if (parent_id > 0) {
                child_id = parent_id + 1;
                ps = connection.prepareStatement(insertQuery);
                ps.setInt(1,parent_id);
                ps.setInt(2,parent.getPtask_id());
                ps.setString(3,parent.getDescription());
                ps.execute();
                ps.setInt(1,child_id);
                ps.setInt(2,parent_id);
                ps.setString(3,child);
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int task_id) {
        String query = "Delete from task where task_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, task_id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(int task_id,String description){
        String query = "Update task set description = ? where task_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,description);
            ps.setInt(2, task_id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Task> getUserTasks(int emp_id) {
        List<Task> tasks = new ArrayList<Task>();
        String query = "SELECT t.task_id,t.ptask_id,t.description,sum(summ) FROM outgo o " +
                "LEFT JOIN (SELECT employee_id,login FROM employee) AS e ON e.employee_id=o.emp_id " +
                "LEFT JOIN (SELECT task_id,ptask_id,description FROM task) AS t ON t.task_id=o.task_id WHERE emp_id=? " +
                "GROUP BY t.task_id,t.ptask_id,t.description";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, emp_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task tmptask = new Task();
                tmptask.setDescription(rs.getString("description"));
                tmptask.setTask_id(rs.getInt("task_id"));
                tasks.add(tmptask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Map<Integer,String> getNextLvl(int task_id) {
        Map<Integer, String> res = new HashMap<>();
        String query = "Select task_id,description from task where ptask_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, task_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.put(rs.getInt("task_id"), rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

//    public List<Task> getNormalTree(){
//        List<Task> parents = getParentsList();
//        List<Task> res = new ArrayList<>();
//        for (Task task:parents) {
////            res.addAll(Test(task.getTask_id()));
//            List<Task> tmpForRes = new ArrayList<>();
//            tmpForRes.add(task);
//            List<Task> tmp = new ArrayList<>(getUpLvl(task.getTask_id()));
//            for (Task t :
//                    tmp) {
//                tmpForRes.add(t);
//                tmpForRes.addAll(getUpLvl(t.getTask_id()));
//            }
//            res.addAll(tmpForRes);
//        }
//        return res;/*Test(new ArrayList<Task>(),1);*/
//    }

    public List<Task> getNormalTree(List<Task> res,int task_id){
        res.add(getTaskById(task_id));
        List<Task> tmp = getUpLvl(task_id);
        if (tmp==null){
            return res;
        }
        for (Task task:
             tmp) {
            getNormalTree(res,task.getTask_id());
        }
        return res;
    }

    private List<Task> getUpLvl(int task_id){
        List<Task> res = new ArrayList<>();
        String query = "Select task_id,ptask_id,description from task where ptask_id = ?";
        try (Connection connection = dao.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,task_id);
            ResultSet rs = ps.executeQuery();
            if(rs==null) return null;
            setTaskHelper(res, rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    private void setTaskHelper(List<Task> res, ResultSet rs) throws SQLException {
        while (rs.next())
        {
            Task task = new Task();
            task.setTask_id(rs.getInt("task_id"));
            task.setPtask_id(rs.getInt("ptask_id"));
            task.setDescription(rs.getString("description"));
            res.add(task);
        }
    }

    public Task getTaskById(int task_id) {
        Task res = new Task();
        String query = "select * from task where task_id=?";
        try(Connection connection = dao.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,task_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                res.setTask_id(rs.getInt(1));
                res.setPtask_id(rs.getInt(2));
                res.setDescription(rs.getString(3));
                res.setOrganization(rs.getString(4));
                res.setArchival(rs.getBoolean(5));
            }}
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
    }

    public List<Task> getParentsList(int someint) {
        List<Task> descrs = new ArrayList<>();
        String query = "Select task_id,ptask_id,description from task where ptask_id IS NULL";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            setTaskHelper(descrs, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return descrs;
    }

    public void addProject(Task project) {
        String getMax = "SELECT max(task_id) FROM task";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getMax);
            ResultSet rs = ps.executeQuery();
            int id = 0;
            while (rs.next())
                id = rs.getInt("max");
            if (id > 0) {
                String query = "INSERT INTO task(" +
                                "task_id, ptask_id, description, org_name, isarchival) values(?,null,?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setInt(1,project.getTask_id());
                ps.setString(2,project.getDescription());
                ps.setString(3,project.getOrganization());
                ps.setBoolean(4,project.isArchival());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override //Изменить на prepared statements
    public List<Task> getAll(boolean withArchival) {
        List<Task> taskList = new ArrayList<>();
        ResultSet rs;
        Task task;
        if (withArchival){
            rs = dao.execSQL("select * from task ORDER BY task_id ASC");
        }
        else {
            rs = dao.execSQL("select * from task where isarchival=false ORDER BY task_id ASC");
        }
        try {
            while (rs.next()) {
                if (rs.getInt("ptask_id") == 0) {
                    task = new Task(rs.getInt("task_id"), 0, rs.getString("description"));
                } else {

                    task = new Task(rs.getInt("task_id"), rs.getInt("ptask_id"), rs.getString("description"));
                }
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public Map<Integer,String> getParents() {
        Map<Integer, String> descrs = new HashMap<>();
        String query = "SELECT task_id,description FROM task WHERE ptask_id IS NULL";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
//                if (!rs.getString("description").equals("Проект"))
                descrs.put(rs.getInt("task_id"), rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return descrs;
    }

    @Override
    public Map<Integer,String> getDescriptionsByTaskId(int task_id) {
        Map<Integer, String> res = new HashMap<>();
        String query = "WITH RECURSIVE r AS (" +
                " SELECT task_id, ptask_id, description, 1 AS level" +
                " FROM task" +
                " WHERE task_id = ?" +
                " UNION ALL" +
                " SELECT task.task_id, task.ptask_id, task.description, r.level + 1 AS level" +
                " FROM task" +
                " JOIN r" +
                " ON task.ptask_id = r.task_id)" +
                " SELECT * FROM r";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,task_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") != 1) {
                    res.put(rs.getInt("task_id"), rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getDescriptionbyTaskId(int task_id) {
        ResultSet rs = dao.execSQL(String.format("Select description from task where task_id='%1$s'",task_id));
        try{
            while (rs.next()){
                return rs.getString("description");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    //Находит родителя самого нижнего уровня
    public Task getTaskParent(int id) {
        ResultSet rs = dao.execSQL(String.format("select task_id,ptask_id,description from task where task_id='%1$s'", id));
        Task task = null;
        try {
            while (rs.next()) {
                if (rs.getInt("ptask_id") == 0)
                    return new Task(rs.getInt("task_id"), rs.getInt("ptask_id"), rs.getString("description"));
                task = getTaskParent(rs.getInt("ptask_id"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
       return task;
    }

    public int getParentTaskId(int task_id){
        String query = "Select ptask_id from task where task_id = ?";
        int res=-1;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,task_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                res =  rs.getInt("ptask_id");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return res;
    }


}
