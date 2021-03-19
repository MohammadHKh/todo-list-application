package utility;

public class Messages {
    public static void sortingOptions() {
        System.out.println("Please Enter 1- Ascending  2- Descending");
    }

    public static void printMenu() {
        String menu = "\nPlease pick an option\n" +
                "1- Show Task list\n" +
                "2- Add New Task\n" +
                "3- Edit Task\n" +
                "4- Load Tasks From File\n" +
                "5- Display Tasks Sorted by Date\n" +
                "6- Display Tasks Sorted by Project\n" +
                "7- Save Tasks to File\n" +
                "8- Exit Without Save\n" +
                "---------------------";

        System.out.println(menu);
    }

    public static void welcomeMessage() {
        System.out.println("Welcome to Todo List Application");
    }

    public static void taskNumberMessage() {
        System.out.println("Enter The Task Title:");
    }

}
