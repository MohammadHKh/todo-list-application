import model.Menu;
import model.ToDoList;
import model.UserCommand;

public class Main {
    public static void main(String[] args) {
        UserCommand toDoList = new ToDoList();
        Menu menu = new Menu(toDoList);
        menu.runTerminal();
    }
}
