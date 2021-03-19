package model;

import java.util.ArrayList;
import java.util.List;

public abstract class UserCommand {
     protected ArrayList<Task> taskList;
     public abstract void sortTaskByProject();
     public abstract void sortTaskByDueDate();
     public abstract void displayTask(List<Task> list);
     public abstract void addTask();
     public abstract void editTask();
     public abstract void saveToFileAsObject();
     public abstract ArrayList<Task> readFromFileAsObject();
}
