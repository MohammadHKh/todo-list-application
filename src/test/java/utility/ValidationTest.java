package utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Validation class
 *
 * @author Mohammad Hossein Khezrian
 */
public class ValidationTest {

    /**
     *  Assert that empty list of task is not valid and should throw IndexOutOfBoundsException.
     */
    @Test
    public void validateTaskListSizeShouldThrowIndexOutOfBoundsExceptionWithEmptyList() {
        List<Task> taskArrayList = new ArrayList<>();
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> Validation.validateListSize(taskArrayList)
        );
    }

    /**
     *  Assert that non-empty list of task is valid.
     */
    @Test
    public void validateTaskListSizeShouldNotThrowExceptionWithNonEmptyList()  {
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("Test", "Test2", "DONE", new Date()));
        assertDoesNotThrow(
                () -> Validation.validateListSize(taskArrayList)
        );
    }

    /**
     *  Assert that accessing elements out of the range of task list size is not valid and should throw IndexOutOfBoundsException.
     */
    @Test
    public void validateTaskListIndexShouldThrowIndexOutOfBoundsExceptionWithIndexOutOfListSize() {
        List<Task> taskArrayList = new ArrayList<>();
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> Validation.validateTaskListIndex(taskArrayList,0)
        );
    }

    /**
     *  Assert that accessing elements in the range of task list size is valid and should not throw IndexOutOfBoundsException.
     */
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

    /**
     * Assert that setting the Task status with anything except "DONE" and "InProgrss" (CaseInSensitive) is not valid and should throw IllegalArgumentException
     */
    @Test
    public void validateTaskStatusShouldThrowIllegalArgumentExceptionWithInvalidStatus() {
        String taskStatus="doneE";
        assertThrows(
                IllegalArgumentException.class,
                () -> Validation.validateTaskStatus(taskStatus)
        );
    }

    /**
     * Assert that setting the Task status with only "DONE" or "InProgress" (CaseInSensitive) is valid and should not throw IllegalArgumentException
     */
    @Test
    public void validateTaskStatusShouldNotThrowExceptionWithValidStatus() {
        String taskStatus="inPRoGresS";
        assertDoesNotThrow(
                () -> Validation.validateTaskStatus(taskStatus)
        );
    }

    /**
     * Assert that setting the Task fields (String) with empty values are not valid and should throw IllegalArgumentException
     */
    @Test
    public void validateStringShouldThrowIllegalArgumentExceptionWithWhiteSpaces() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Validation.validateStringField("  ")
        );
    }

    /**
     * Assert that setting the Task fields (String) with non-empty values is valid and should not throw IllegalArgumentException
     */
    @Test
    public void validateStringShouldNotThrowIExceptionWithNotBlankValues() {
        assertDoesNotThrow(
                () -> Validation.validateStringField(" f ")
        );
    }
}