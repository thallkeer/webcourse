package app.entities;

public class Test {
    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Test(int task_id, Test parent_task, String description) {
        this.task_id = task_id;
        this.parent_task = parent_task;
        this.description = description;
    }

    public Test(){}

    private int task_id;
    private Test parent_task;
    private String description;

    public Test getParent_task() {
        return parent_task;
    }

    public void setParent_task(Test parent_task) {
        this.parent_task = parent_task;
    }
}
