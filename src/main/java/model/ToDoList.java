package model;

import utility.Messages;
import utility.Validation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Provides methods for Arraylist of Tasks such as Adding, Deleting, Updating, Sorting, Saving to file, and Reading from file.
 *
 * @author Mohammad Hossein Khezrian
 */
public class ToDoList extends UserCommand {

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    /**
     * Determines if the user wants to sort in ascending or descending order the Tasks by DueDate.
     * If user chooses ascending this method calls sortTaskByProjectAscending method,
     * otherwise it calls sortTaskByProjectDescending method.
     */
    public void sortTaskByDueDate() {
        try {
            Validation.validateListSize(taskList);
            Messages.sortingOptions();
            Scanner userInput = new Scanner(System.in);
            int sortType = userInput.nextInt();
            switch (sortType) {
                case 1:
                    sortTaskByDueDateAscending();
                    break;
                case 2:
                    sortTaskByDueDateDescending();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * Sorts Tasks by DueDate in descending order.
     */
    private void sortTaskByDueDateDescending() {
        List<Task> sortedTaskListByDateDescending = taskList.stream().sorted(Comparator.comparing(Task::getDueDate).reversed()).collect(Collectors.toList());
        displayTask(sortedTaskListByDateDescending);
    }

    /**
     * Sorts Tasks by DueDate in ascending order.
     */
    private void sortTaskByDueDateAscending() {
        List<Task> sortedTaskListByDateAscending = taskList.stream().sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
        displayTask(sortedTaskListByDateAscending);
    }

    /**
     * Determines if the user wants to sort in ascending or descending order the Tasks by ProjectName.
     * If user chooses ascending this method calls sortTaskByProjectAscending method,
     * otherwise it calls sortTaskByProjectDescending method.
     */
    public void sortTaskByProject() {
        try {
            Validation.validateListSize(taskList);
            Messages.sortingOptions();
            Scanner userInput = new Scanner(System.in);
            int sortType = userInput.nextInt();
            switch (sortType) {
                case 1:
                    sortTaskByProjectAscending();
                    break;
                case 2:
                    sortTaskByProjectDescending();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * Sorts Tasks by ProjectName in descending order.
     */
    private void sortTaskByProjectDescending() {
        List<Task> sortedTaskListByProjectDescending = taskList.stream().sorted(Comparator.comparing(Task::getProjectName).reversed()).collect(Collectors.toList());
        displayTask(sortedTaskListByProjectDescending);
    }

    /**
     * Sorts Tasks by ProjectName in ascending order.
     */
    private void sortTaskByProjectAscending() {
        List<Task> sortedTaskListByProjectAscending = taskList.stream().sorted(Comparator.comparing(Task::getProjectName)).collect(Collectors.toList());
        displayTask(sortedTaskListByProjectAscending);
    }

    /**
     * Displays Tasks by insertion order.
     * @param list ArrayList of Tasks.
     */
    public void displayTask(List<Task> list) {
        try {
            Validation.validateListSize(taskList);
            IntStream.range(0, list.size()).forEach(a -> System.out.println("Task-" + a + list.get(a)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a Task to ArrayList of Tasks.
     */
    public void addTask() {
        Date dueDate = null;
        String taskTilte = null;
        String taskProjectTitle = null;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter new Task:");

        System.out.println("Enter The Task Title:");
        taskTilte = input.nextLine();

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        String taskDueDate = input.nextLine();

        System.out.println("Enter Project Title:");
        taskProjectTitle = input.nextLine();

        try {
            Validation.validateStringField(taskTilte);
            Validation.validateStringField(taskProjectTitle);
            Validation.validateStringField(taskDueDate);
            dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(taskDueDate);
            taskList.add(new Task(taskTilte, taskProjectTitle, dueDate));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        } catch (ParseException e) {
            System.out.println("Error: Please Enter Date in Correct Format: YYYY-MM-DD ");
            return;
        }
        System.out.println("Task added Successfully");
    }

    /**
     * Receives user's decision about update a Task, mark a Task as done or remove a Task.
     * If user wants to update a Task it calls updateTask method.
     * If user wants to mark a Task as done it calls markAsDone method.
     * If user wants to remove a Task it calls removeTask method.
     */
    public void editTask() {

        System.out.println("Please Enter Task Number:");
        Scanner input = new Scanner(System.in);
        int taskNumber = input.nextInt();
        try {
            Validation.validateTaskListIndex(taskList, taskNumber);
            System.out.println("Please Enter 1- Update  2-Mark As Down  3-Remove");
            Scanner userInput = new Scanner(System.in);
            int updateType = userInput.nextInt();
            switch (updateType) {
                case 1:
                    updateTask(taskNumber);
                    break;
                case 2:
                    markAsDone(taskNumber);
                    break;
                case 3:
                    removeTask(taskNumber);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes a specific Task from ArrayList of Tasks.
     * @param taskNumber Desired Task number.
     */
    private void removeTask(int taskNumber) {
        taskList.remove(taskNumber);
        System.out.println(("Task-" + taskNumber + "Successfully Removed"));
    }

    /**
     * Updates a specific Task Status to Done.
     * @param taskNumber Desired Task number.
     */
    private void markAsDone(int taskNumber) {
        String taskStatus = null;
        Task task = taskList.get(taskNumber);
        System.out.println("Enter Task Status In The Format DONE or InProgress (Case Insensitive) :");
        Scanner input = new Scanner(System.in);
        taskStatus = input.nextLine();
        try {
            Validation.validateTaskStatus(taskStatus);
            task.setStatus(taskStatus);
            System.out.println(("Task Updated successfully"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Task Is Not Modified");
    }

    /**
     * Updates an specific Task.
     * All fields should have a value.
     * @param taskNumber Desired Task number.
     */
    private void updateTask(int taskNumber) {
        Date dueDate = null;
        String stringDate = null;
        boolean isUpdated = false;

        Task task = taskList.get(taskNumber);
        System.out.println("Enter the details");
        System.out.println("Enter Task Title:");
        Scanner input = new Scanner(System.in);
        String title = input.nextLine();
        System.out.println("Enter Task Project Title:");
        String projectTitle = input.nextLine();

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        stringDate = input.nextLine();

        try {
            Validation.validateStringField(title);
            Validation.validateStringField(projectTitle);
            Validation.validateStringField(stringDate);
            dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(stringDate);
            task.setTitle(title);
            task.setProjectName(projectTitle);
            task.setDueDate(dueDate);
            isUpdated = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        } catch (ParseException e) {
            System.out.println("Error: Please Enter Date in Correct Format: YYYY-MM-DD ");
            return;
        }
        System.out.println(("Task" + (isUpdated ? "Updated Successfully" : "Is Not Modified")));
    }

    /**
     * Saves ArrayList of Tasks as an object in a file.
     */
    public void saveToFileAsObject() {
        try {
            File file = new File("C:\\Users\\Mohammad\\Downloads\\todo-list-application\\src\\main\\java\\Data\\File.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
            writer.writeObject(taskList);
            System.out.println("File successfully saved");
            writer.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an ArrayList of Tasks from a file as an object.
     * @return  ArrayList of Tasks.
     */
    public ArrayList<Task> readFromFileAsObject() {

        try (ObjectInputStream reader = new ObjectInputStream(
                new FileInputStream(
                        new File("C:\\Users\\Mohammad\\Downloads\\todo-list-application\\src\\main\\java\\Data\\File.txt")))) {
            taskList = (ArrayList<Task>) reader.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There Is No File");
            e.printStackTrace();
        }
        System.out.println("File successfully loaded");
        return taskList;
    }

}
