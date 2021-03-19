package utility;

import model.Task;

import java.util.List;

public class Validation {
    public static void validateListSize(List<Task> taskList) {
        if (taskList.size() > 0) {
            return;
        }
        throw new IndexOutOfBoundsException("There is no Task");
    }
    public static void validateTaskListIndex(List<Task> taskList, int taskNumber) {

        if (taskNumber >= 0 && taskNumber < taskList.size()) {
            return;
        } else throw new IndexOutOfBoundsException("Error, Please Enter Valid Task Number, There is no task-" + taskNumber);
    }

    public static void validateTaskStatus(String taskStatus) {

        if (taskStatus.equalsIgnoreCase("DONE") || taskStatus.equalsIgnoreCase("InProgress")) {
            return;
        } else
            throw new IllegalArgumentException("Error, Please Enter Task Status In The Format DONE or InProgress (Case Insensitive)");
    }
    public static void validateStringField(String field) {

        if (!field.isBlank()) {
            return;
        } else
            throw new IllegalArgumentException("Error, Field Can Not Be Null");
    }
}
