package model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoListTest {

    @Test
    void saveToFileAsObject() {
    }

    boolean compareLists(ArrayList<Task> list1, ArrayList<Task> list2) {
        boolean compareflag = true;
        if (list1.size() != list2.size())
            return false;

        for (int i = 0; i < list1.size(); i++) {
            compareflag = compareflag && list1.get(i).getDueDate().equals(list2.get(i).getDueDate()) &&
                    list1.get(i).getProjectName().equals(list2.get(i).getProjectName()) &&
                    list1.get(i).getStatus().equals(list2.get(i).getStatus()) &&
                    list1.get(i).getTitle().equals(list2.get(i).getTitle());
        }
        return compareflag;
    }

    @Test
    void readFromFileAsObject() {

        ArrayList<Task> expectedArrayList = new ArrayList<>();
        expectedArrayList.add(new Task("Test0", "Test2", "DONE", new Date(2020 - 10 - 10)));
        expectedArrayList.add(new Task("Test1", "Test2", "DONE", new Date(2020 - 10 - 10)));
        expectedArrayList.add(new Task("Test2", "Test2", "DONE", new Date(2020 - 10 - 10)));

        UserCommand toDoList = new ToDoList();
        toDoList.taskList.add(new Task("Test0", "Test2", "DONE", new Date(2020 - 10 - 10)));
        toDoList.taskList.add(new Task("Test1", "Test2", "DONE", new Date(2020 - 10 - 10)));
        toDoList.taskList.add(new Task("Test2", "Test2", "DONE", new Date(2020 - 10 - 10)));

        toDoList.saveToFileAsObject();
        ArrayList<Task> actualTaskList = toDoList.readFromFileAsObject();
        assertEquals(true, compareLists(expectedArrayList, actualTaskList));

    }
}