import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ToDoList {
    int choice;
    private boolean exit;
    private ArrayList<Task> tasklist;

    public ToDoList() {
        tasklist = new ArrayList<>();
    }

    private static void printMenu() {
        String menu = "\nPlease pick an option\n" +
                "1- Show Task list\n" +
                "2- Add New Task\n" +
                "3- Edit Task\n" +
                "4- Load Tasks From File\n" +
                "5- Save and Exit\n" +
                "6- Exit Without Save\n"+
                "---------------------";

        System.out.println(menu);
    }

    public void runTerminal() {
        System.out.println("Welcome to Todo List Application");
        while (!exit) {

            Scanner input = new Scanner(System.in);
            printMenu();
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    displayTask();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    tasklist = readFromFileAsObject();
                    break;
                case 5:
                    saveToFileAsObject();
                    break;
                default:
                    exit=true;
            }
        }
        System.exit(0);

    }

    public void displayTask() {
        if (tasklist.size() > 0)
            IntStream.range(0, tasklist.size()).forEach(a -> System.out.println("Task-" + a + tasklist.get(a)));
        else
            System.out.println("There is no task");
    }

    public void addTask() {
        Date dueDate = null;
        String taskTilte=null;
        String taskProjectTitle=null;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter new Task:");

        System.out.println("Enter The Task Title:");
        taskTilte = input.nextLine();
        if (taskTilte.isBlank() || taskTilte.isEmpty()){
            System.out.println("Error: Task Title Can Not Be Null");
            return;
        }

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        String taskDueDate = input.nextLine();
        try {
            dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(taskDueDate);
        } catch (ParseException e) {
            System.out.println("Error: Please Enter Date in Correct Format: YYYY-MM-DD");
            return;
        }

        System.out.println("Enter The Task Status In The Correct Format: DONE or InProgress (Case Insensitive) :");
        String taskStatus = input.nextLine();
        if (!taskStatus.equalsIgnoreCase("DONE") && !taskStatus.equalsIgnoreCase("InProgress")){
            System.out.println("Error: Please Enter The Task Status In The Correct Format (Case Insensitive): DONE or InProgress :");
            return;
        }

        System.out.println("Enter Project Title:");
        taskProjectTitle = input.nextLine();
        if (taskProjectTitle.isBlank() || taskDueDate.isEmpty()){
            System.out.println("Error: Project Title Can Not Be Null");
            return;
        }

        tasklist.add(new Task(taskTilte, taskProjectTitle, taskStatus, dueDate));
    }


    public void editTask() {

        System.out.println("Please Enter Task Number:");
        Scanner input = new Scanner(System.in);
        int taskNumber = input.nextInt();
        if (taskNumber < 0 || taskNumber > tasklist.size() - 1){
            System.out.println("Error, Please Enter Valid Task Number, There is no task-" + taskNumber);
            return;
        }
         //   throw new IndexOutOfBoundsException("Please Enter Valid Task Number, There is no task-" + taskNumber);

        System.out.println("Please Enter 1- Update  2-Mark As Down  3-Remove");
        Scanner userInput = new Scanner(System.in);
        int updateType = userInput.nextInt();
        switch (updateType) {
            case 1:
                updateTask(taskNumber);
                break;
            case 2:
                markAsDown(taskNumber);
                break;
            case 3:
                removeTask(taskNumber);
                break;
        }
    }

    private void removeTask(int taskNumber) {
        tasklist.remove(taskNumber);
        System.out.println(("Task-" + taskNumber + "Successfully Removed"));
    }

    private void markAsDown(int taskNumber) {
        String taskStatus=null;
        Task task = tasklist.get(taskNumber);
        System.out.println("Enter Task Status In The Format DONE or InProgress (Case Insensitive) :");
        Scanner input = new Scanner(System.in);
        taskStatus = input.nextLine();



        if (taskStatus.equalsIgnoreCase("DONE") || taskStatus.equalsIgnoreCase("InProgress")){
            task.setStatus(taskStatus);
            System.out.println(("Task Updated successfully"));
            return;
        }
        System.out.println("Task Is Not Modified");
    }

    private void updateTask(int taskNumber) {
        Date dueDate=null;
        String stringDate=null;
        Task task = tasklist.get(taskNumber);
        System.out.println("Enter the details");

        System.out.println("Enter Task Title:");
        Scanner input = new Scanner(System.in);
        String title = input.nextLine();
        boolean isUpdated = false;

        if (!title.isBlank() && !title.isEmpty()) {
            task.setTitle(title);
            isUpdated = true;
        }

        System.out.println("Enter Task Project Title:");
        String projectTitle = input.nextLine();
        if (!projectTitle.isBlank() && !projectTitle.isEmpty()) {
            task.setProjectName(projectTitle);
            isUpdated = true;
        }

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        stringDate = input.nextLine();
        if (!stringDate.isEmpty() && !stringDate.isBlank())
            try {
                dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(stringDate);
                task.setDueDate(dueDate);
                isUpdated = true;
            } catch (ParseException e) {
                System.out.println("Error: Please Enter Date in Correct Format: YYYY-MM-DD ");
                return;
            }

        System.out.println(("Task" + (isUpdated ? "Updated Successfully" : "Is Not Modified")));
    }

    private void saveToFileAsObject() {
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
        exit = true;
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
