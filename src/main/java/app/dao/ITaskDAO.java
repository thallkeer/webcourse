package app.dao;

import app.entities.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITaskDAO {
    public List<Task> getUserTasks(String login);
    public Map<Integer,String> getDescriptionByTaskId(Integer task_id);
    public List<String> getParents();
    public Map<Integer,String> getNextLvl(Integer task_id);
    public List<Task> getAll() throws SQLException;
    public Task getTasksTree(Integer id) throws SQLException;
    public void addProject(Task project);
}
