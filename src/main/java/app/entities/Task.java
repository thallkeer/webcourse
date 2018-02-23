package app.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer task_id;

    @ManyToOne (fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn (name="task_id")
    private Task ptask_id;

    @Column(name = "description")
    private String description;

    @Column(name = "org_name")
    private String organisation;

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(final Integer task_id) {
        this.task_id = task_id;
    }

    public Task getPtask_id() {
        return ptask_id;
    }

    public void setPtask_id(Task ptask_id) {
        this.ptask_id = ptask_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(final String organisation) {
        this.organisation = organisation;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", ptask_id='" + ptask_id + '\'' +
                ", description='" + description + '\'' +
                ", organisation='" + organisation + '\'' +
                '}';
    }
}
