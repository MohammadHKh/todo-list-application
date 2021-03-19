package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Defines a Task with title, projectName, status, and dueDate attributes.
 *
 * @author Mohammad Hossein Khezrian
 */
public class Task implements Serializable {
    /**
     * The title of the Task
     * @serial
     */
    private String title;

    /**
     * The project's name of the Task
     * @serial
     */
    private String projectName;

    /**
     * The status of the Task (it can be "DONE" or "InProgress")
     * @serial
     */
    private String status;

    /**
     * The dueDate of the Task (the latest date for accomplishing the task
     * @serial
     */
    private Date dueDate;

    /**
     * Constructs a Task with the specified fields.
     * @param title the title of Task.
     * @param projectName the project's name of Task.
     * @param status the status of Task.
     * @param dueDate the dead-line for accomplishing the task.
     */
    public Task(String title, String projectName, String status, Date dueDate) {
        this.title = title;
        this.projectName = projectName;
        this.status = status;
        this.dueDate = dueDate;
    }

    /**
     * Returns the Task title.
     * @return the Task title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Task title.
     * @param title Task title;
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the Task project name.
     * @return the task project title.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets Task project name.
     * @param projectName Task project name.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Returns the Task due date.
     * @return the task due date.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets Task due date.
     * @param dueDate Task due date.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the Task status.
     * @return ths Task status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets Task status.
     * @param status Task status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Indicates whether some other Task object is "equal to" this one.
     * @param o the reference object with which to compare.
     * @return {@code true} if this Task object is the same as the obj
     *      *          argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return status == task.status &&
                title.equals(task.title) &&
                projectName.equals(task.projectName) &&
                dueDate.equals(task.dueDate);
    }

    /**
     * Returns a hash code value for the Task object.
     * @return a hash code value for this Task object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, projectName, status, dueDate);
    }

    /**
     * Returns a string representation of the Task object.
     * @return a string representation of the Task object.
     */
    @Override
    public String toString() {
        return "{\n" +
                "  title=" + title + "\n" +
                "  projectName=" + projectName + "\n" +
                "  status=" + status.toUpperCase() +"\n"+
                "  dueDate=" + dueDate +"\n"+
                '}';
    }
}
