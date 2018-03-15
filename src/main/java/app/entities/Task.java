package app.entities;

public class Task {

    private Integer task_id;
   private Integer ptask_id;
//    private Task parent_task;
    private String description;
    private String organization;
    private boolean isArchival;


    public Task(){

    }

    public Task(Integer task_id,Integer ptask_id,String description){
        this.task_id=task_id;
        this.ptask_id=ptask_id;
        this.description = description;
    }

    public Task(Integer task_id,Integer ptask_id,String description,String organization,boolean isArchival){
        this.task_id=task_id;
        this.ptask_id=ptask_id;
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
                ", parent_task='" + ptask_id + '\'' +
                ", description='" + description + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }

//    public Task getParent_task() {
//        return parent_task;
//    }
//
//    public void setParent_task(Task parent_task) {
//        this.parent_task = parent_task;
//    }

    public boolean isArchival() {
        return isArchival;
    }

    public void setArchival(boolean archival) {
        isArchival = archival;
    }

    public Integer getPtask_id() {
        return ptask_id;
    }

    public void setPtask_id(Integer ptask_id) {
        this.ptask_id = ptask_id;
    }
}
