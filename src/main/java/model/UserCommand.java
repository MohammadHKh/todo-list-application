package model;

import java.util.ArrayList;
import java.util.List;

public abstract class UserCommand {
     ArrayList<Task> tasklist;
     abstract void sortTaskByProject();
     abstract void sortTaskByDueDate();
     abstract void displayTask(List<Task> list);
     abstract void addTask();
     abstract void editTask();
     abstract void saveToFileAsObject();
     abstract ArrayList<Task> readFromFileAsObject();
}
