import java.time.LocalDate;
import java.util.ArrayList;
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
        String menu = "Please pick an option\n" +
                      "1- Show Task list\n" +
                      "2- Add New Task\n" +
                      "3- Edit Task\n" +
                      "4- Save and Exit\n";
        System.out.println(menu);
    }

    public void runTerminal() {
        System.out.println("Welcome to Todo List Application\n");
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
                    saveToFile();
                    break;
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
        Scanner input = new Scanner(System.in);
        System.out.println("Enter new Task:");

        System.out.println("Enter The Task Title:");
        String taskTilte = input.nextLine();

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        String taskDueDate = input.nextLine();

        System.out.println("Enter The Task Status In The Format DONE or InProgress :");
        String taskStatus = input.nextLine();

        System.out.println("Enter Project Title:");
        String taskProjectTitle = input.nextLine();

        tasklist.add(new Task(taskTilte, taskProjectTitle, taskStatus, LocalDate.parse(taskDueDate)));
    }


    public void editTask() {

        System.out.println("Please Enter Task Number:");
        Scanner input = new Scanner(System.in);
        int taskNumber = input.nextInt();
        if (taskNumber < 0 || taskNumber > tasklist.size() - 1)
            throw new IndexOutOfBoundsException("Please Enter Valid Task Number, There is no task-" + taskNumber);

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
        System.out.println(("Task-"+ taskNumber+ "Successfully Removed"));
    }

    private void markAsDown(int taskNumber) {
        Task task = tasklist.get(taskNumber);
        System.out.println("Enter Task Status In The Format DONE or InProgress :");
        Scanner input = new Scanner(System.in);
        String taskStatus = input.nextLine();
        boolean isUpdated = false;

        if (taskStatus != null) {
            task.setStatus(taskStatus);
            isUpdated = true;
        }
        System.out.println(("Task is" + (isUpdated ? "Task Updated successfully" : "Task Is Not Modified")));
    }

    private void updateTask(int taskNumber) {
        Task task = tasklist.get(taskNumber);
        System.out.println("Enter the details");

        System.out.println("Enter Task Title:");
        Scanner input = new Scanner(System.in);
        String title = input.nextLine();
        boolean isUpdated=false;

        if (title!=null){
            task.setTitle(title);
            isUpdated = true;
        }

        System.out.println("Enter Task Project Tile");
        String projectTitle = input.nextLine();
        if (projectTitle!=null){
            task.setProjectName(projectTitle);
            isUpdated = true;
        }

        System.out.println("Enter The Task Due Date In The Format YYYY-MM-DD:");
        String dueDate = input.nextLine();
        if (dueDate!=null){
            task.setDueDate(LocalDate.parse(dueDate));
            isUpdated = true;
        }

        System.out.println(("Task is"+ (isUpdated ? "Task Updated Successfully":"Task Is Not Modified")));
    }

    private void saveToFile() {
        exit = true;
    }

}