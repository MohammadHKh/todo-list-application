import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Serializable {
    private String title;
    private String projectName;
    private String status;
    private LocalDate dueDate;


    public Task(String title, String projectName, String status, LocalDate dueDate) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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
                "  status=" + status +"\n"+
                "  dueDate=" + dueDate +"\n"+
                '}';
    }
}
