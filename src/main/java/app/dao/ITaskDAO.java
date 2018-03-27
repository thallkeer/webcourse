package app.dao;

import app.entities.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITaskDAO {

    public void addCategory(Task task);
    public void addCategory(Task parent,String child);
    public List<Task> getUserTasks(String login);
    public Map<Integer,String> getDescriptionsByTaskId(int task_id);
    public Map<Integer,String> getParents();
    public Map<Integer,String> getNextLvl(int task_id);
    public Task getTaskById(int task_id);

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
    public List<Task> getTasksTreeByTaskId(int task_id);

}
