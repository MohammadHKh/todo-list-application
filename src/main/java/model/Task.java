package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Task implements Serializable {
    private String title;
    private String projectName;
    private String status;
    private Date dueDate;


    public Task(String title, String projectName, String status, Date dueDate) {
        this.title = title;
        this.projectName = projectName;
        this.status = status;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(title, projectName, status, dueDate);
    }

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
