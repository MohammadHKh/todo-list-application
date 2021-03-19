package model;

import utility.Messages;
import utility.Validation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToDoList extends UserCommand {

    public ToDoList() {
        tasklist = new ArrayList<>();
    }

    public void sortTaskByDueDate() {
        try {
            Validation.validateListSize(tasklist);
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

    private void sortTaskByDueDateDescending() {
        List<Task> sortedTaskListByDateDescending = tasklist.stream().sorted(Comparator.comparing(Task::getDueDate).reversed()).collect(Collectors.toList());
        displayTask(sortedTaskListByDateDescending);
    }

    private void sortTaskByDueDateAscending() {
        List<Task> sortedTaskListByDateAscending = tasklist.stream().sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
        displayTask(sortedTaskListByDateAscending);
    }

    public void sortTaskByProject() {
        try {
            Validation.validateListSize(tasklist);
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

    private void sortTaskByProjectDescending() {
        List<Task> sortedTaskListByProjectDescending = tasklist.stream().sorted(Comparator.comparing(Task::getProjectName).reversed()).collect(Collectors.toList());
        displayTask(sortedTaskListByProjectDescending);
    }

    private void sortTaskByProjectAscending() {
        List<Task> sortedTaskListByProjectAscending = tasklist.stream().sorted(Comparator.comparing(Task::getProjectName)).collect(Collectors.toList());
        displayTask(sortedTaskListByProjectAscending);
    }

    public void displayTask(List<Task> list) {
        try {
            Validation.validateListSize(tasklist);
            IntStream.range(0, list.size()).forEach(a -> System.out.println("Task-" + a + list.get(a)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

        System.out.println("Enter The Task Status In The Correct Format: DONE or InProgress (Case Insensitive) :");
        String taskStatus = input.nextLine();

        System.out.println("Enter Project Title:");
        taskProjectTitle = input.nextLine();

        try{
            Validation.validateStringField(taskTilte);
            Validation.validateStringField(taskProjectTitle);
            Validation.validateTaskStatus(taskStatus);
            Validation.validateStringField(taskDueDate);
            dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(taskDueDate);
            tasklist.add(new Task(taskTilte, taskProjectTitle, taskStatus, dueDate));
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        catch (ParseException e){
            System.out.println("Error: Please Enter Date in Correct Format: YYYY-MM-DD ");
            return;
        }
        System.out.println("Task added Successfully");
    }

    public void editTask() {

        System.out.println("Please Enter Task Number:");
        Scanner input = new Scanner(System.in);
        int taskNumber = input.nextInt();
        try{
            Validation.validateTaskListIndex(tasklist,taskNumber);
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
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void removeTask(int taskNumber) {
        tasklist.remove(taskNumber);
        System.out.println(("Task-" + taskNumber + "Successfully Removed"));
    }

    private void markAsDone(int taskNumber) {
        String taskStatus = null;
        Task task = tasklist.get(taskNumber);
        System.out.println("Enter Task Status In The Format DONE or InProgress (Case Insensitive) :");
        Scanner input = new Scanner(System.in);
        taskStatus = input.nextLine();
        try{
            Validation.validateTaskStatus(taskStatus);
            task.setStatus(taskStatus);
            System.out.println(("Task Updated successfully"));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Task Is Not Modified");
    }

    private void updateTask(int taskNumber) {
        Date dueDate = null;
        String stringDate = null;
        boolean isUpdated = false;

        Task task = tasklist.get(taskNumber);
        System.out.println("Enter the details");
        System.out.println("Enter Task Title:");
        Scanner input = new Scanner(System.in);
        String title = input.nextLine();
        System.out.println("Enter Task Project Title:");
        String projectTitle = input.nextLine();

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        stringDate = input.nextLine();

        try{
            Validation.validateStringField(title);
            Validation.validateStringField(projectTitle);
            Validation.validateStringField(stringDate);
            dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(stringDate);
            task.setTitle(title);
            task.setProjectName(projectTitle);
            task.setDueDate(dueDate);
            isUpdated = true;
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        catch (ParseException e){
            System.out.println("Error: Please Enter Date in Correct Format: YYYY-MM-DD ");
            return;
        }
        System.out.println(("Task" + (isUpdated ? "Updated Successfully" : "Is Not Modified")));
    }

    public void saveToFileAsObject() {
        try {
            File file = new File("C:\\Users\\Mohammad\\Downloads\\todo-list-application\\src\\main\\java\\Data\\File.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
            writer.writeObject(tasklist);
            writer.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readFromFileAsObject() {

        try (ObjectInputStream reader = new ObjectInputStream(
                new FileInputStream(
                        new File("C:\\Users\\Mohammad\\Downloads\\todo-list-application\\src\\main\\java\\Data\\File.txt")))) {
            tasklist = (ArrayList<Task>) reader.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There Is No File");
            e.printStackTrace();
        }
        return tasklist;
    }

}
