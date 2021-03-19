package utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ValidationTest {

    @Test
    public void validateTaskListSizeShouldThrowIndexOutOfBoundsExceptionWithEmptyList() {
        List<Task> taskArrayList = new ArrayList<>();
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> Validation.validateListSize(taskArrayList)
        );
    }

    @Test
    public void validateTaskListSizeShouldNotThrowExceptionWithNonEmptyList()  {
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("Test", "Test2", "DONE", new Date()));
        assertDoesNotThrow(
                () -> Validation.validateListSize(taskArrayList)
        );
    }

    @Test
    public void validateTaskListIndexShouldThrowIndexOutOfBoundsExceptionWithIndexOutOfListSize() {
        List<Task> taskArrayList = new ArrayList<>();
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> Validation.validateTaskListIndex(taskArrayList,0)
        );
    }

    @Test
    public void validateTaskListIndexShouldNotThrowExceptionWithIndexInRangeOfListSize() {
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("Test0", "Test2", "DONE", new Date()));
        taskArrayList.add(new Task("Test1", "Test2", "DONE", new Date()));
        taskArrayList.add(new Task("Test2", "Test2", "DONE", new Date()));
        assertDoesNotThrow(
                () -> Validation.validateTaskListIndex(taskArrayList, 2)
        );
    }

    @Test
    public void validateTaskStatusShouldThrowIllegalArgumentExceptionWithInvalidStatus() {
        String taskStatus="doneE";
        assertThrows(
                IllegalArgumentException.class,
                () -> Validation.validateTaskStatus(taskStatus)
        );
    }

    @Test
    public void validateTaskStatusShouldNotThrowExceptionWithValidStatus() {
        String taskStatus="inPRoGresS";
        assertDoesNotThrow(
                () -> Validation.validateTaskStatus(taskStatus)
        );
    }

    @Test
    public void validateStringShouldThrowIllegalArgumentExceptionWithWhiteSpaces() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Validation.validateStringField("  ")
        );
    }

    @Test
    public void validateStringShouldNotThrowIExceptionWithNotBlankValues() {
        assertDoesNotThrow(
                () -> Validation.validateStringField(" f ")
        );
    }
}