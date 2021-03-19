package model;

import utility.Messages;

import java.util.Scanner;

public class Menu {
    private boolean exit;
    private UserCommand toDoList;

    public Menu(UserCommand command) {
        this.toDoList = command;
    }

    public void runTerminal() {
        int choice;
        Messages.welcomeMessage();
        while (!exit) {

            Scanner input = new Scanner(System.in);
            Messages.printMenu();
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    toDoList.displayTask(toDoList.taskList);
                    break;
                case 2:
                    toDoList.addTask();
                    break;
                case 3:
                    toDoList.editTask();
                    break;
                case 4:
                    toDoList.taskList = toDoList.readFromFileAsObject();
                    break;
                case 5:
                    toDoList.sortTaskByDueDate();
                    break;
                case 6:
                    toDoList.sortTaskByProject();
                    break;
                case 7:
                    toDoList.saveToFileAsObject();
                    break;
                default:
                    exit = true;
            }
        }
        System.exit(0);

    }
}
