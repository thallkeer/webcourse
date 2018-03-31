package app.dao;

import app.entities.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITaskDAO {

    public void addCategory(Task task);
    public void addCategory(Task parent,String child);
    public void delete(int task_id);
    public void update(int task_id,String description);
    public List<Task> getUserTasks(int emp_id);
    public Map<Integer,String> getDescriptionsByTaskId(int task_id);
    public Map<Integer,String> getParents();
    public Map<Integer,String> getNextLvl(int task_id);
    public Task getTaskById(int task_id);


    public List<Task> getNormalTree(List<Task> res,int task_id);
    public List<Task> getParentsList(int someint);

        /**
     указывает на то,возвращать ли архивные проекты* @param withArchival
     * @return
     * @throws SQLException
     */
    public List<Task> getAll(boolean withArchival) ;
    public Task getTaskParent(int id) ;
    public void addProject(Task project);
    public String getDescriptionbyTaskId(int task_id);
    public int getParentTaskId(int task_id);


}
