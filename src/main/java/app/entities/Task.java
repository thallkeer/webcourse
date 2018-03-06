package app.entities;

import java.io.Serializable;


public class Task implements Serializable {

    private Integer task_id;
    private Task parent_task;
    private String description;
    private String organization;
    private boolean isArchival;


    public Task(){

    }

    public Task(Integer task_id,Task parent_task,String description){
        this.task_id=task_id;
        this.parent_task=parent_task;
        this.description = description;
    }

    public Task(Integer task_id,Task parent_task,String description,String organization,boolean isArchival){
        this.task_id=task_id;
        this.parent_task=parent_task;
        this.description = description;
        this.organization=organization;
        this.isArchival = isArchival();
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organisation) {
        this.organization = organisation;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", parent_task='" + parent_task.task_id + '\'' +
                ", description='" + description + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }

    public Task getParent_task() {
        return parent_task;
    }

    public void setParent_task(Task parent_task) {
        this.parent_task = parent_task;
    }

    public boolean isArchival() {
        return isArchival;
    }

    public void setArchival(boolean archival) {
        isArchival = archival;
    }
}
