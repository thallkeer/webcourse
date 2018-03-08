package app.dao;

import app.entities.Task;

import java.util.List;

public interface ITaskDAO {
    public List<Task> getUserTasks(String login);
    public List<Task> getTasksByLvl(Integer lvl);
    public List<String> getTasksByParentId(Integer parent_id);
    public List<String> getDescriptionByIdLvl(Integer task_id,Integer lvl);
    public List<String> getParents();
    public List<String> getNextLvl(int ptask_id);
}
