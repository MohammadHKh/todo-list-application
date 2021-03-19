import java.util.List;

public class Validation {
    public static void validateListSize(List<Task> taskList) {
        if (taskList.size() > 0) {
            return;
        }
        throw new IndexOutOfBoundsException("There is no Task");
    }
}
