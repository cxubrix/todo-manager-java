package lv.rcs.todo.app;

import java.util.List;

import lv.rcs.todo.SignUpException;
import lv.rcs.todo.controller.AppController;
import lv.rcs.todo.controller.SimpleAppController;
import lv.rcs.todo.controller.SimpleToDoController;
import lv.rcs.todo.controller.ToDoController;
import lv.rcs.todo.controller.service.SimpleToDoManager;
import lv.rcs.todo.dto.ToDo;
import lv.rcs.todo.dto.UserDetails;

public class Main {

    public static void main(String[] args) {

        // app
        AppController appController = SimpleAppController.getInstance();
        
        
        UserDetails user = null;
        UserDetails otherUser = null;
        try {
            user = appController.signup("test@rcs.lv", "Kaspars", "Test");
            otherUser = appController.signup("other@rcs.com", "Evil", "123123");
        } catch (SignUpException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println(user);
        System.out.println(otherUser);

        // separate todo controller for each user
        ToDoController todoController = appController.login(user.getEmail());
        System.out.println(todoController);

        ToDoController otherTodoController = appController.login(otherUser.getEmail());
        System.out.println(otherTodoController);

//        ToDoController failController = new SimpleToDoController(new SimpleToDoManager(), new UserDetails("fake"));
//        failController.add("test");

        int id0 = todoController.add("Review projects");
        int id1 = todoController.add("Test app");
        int id2 = todoController.add("Check gradle");
        otherTodoController.add("Get some food");
        int id3 = otherTodoController.add("Drink more water!");

        listToDos(user, todoController.getMyToDos()); // test user todos
        listToDos(otherUser, otherTodoController.getMyToDos()); // other user todos

        // modify user todos
        todoController.deleteToDo(id0);
        todoController.changeStatus(id1, true);
        todoController.updateToDoDescription(id2, "Upload presentation");

        // modify other user todos
        otherTodoController.changeStatus(id3, true);

        listToDos(user, todoController.getMyToDos()); // test user todos
        listToDos(otherUser, otherTodoController.getMyToDos());// other user todos

    }

    private static void listToDos(UserDetails user, List<ToDo> myToDos) {
        System.out.println("-- " + user.getEmail() + " ToDo list (" + myToDos.size() + ")------");
        for (ToDo todo : myToDos) {
            char state = todo.isDone() ? '√' : ' ';
            String date = "";
            if (todo.isDone()) {
                date = " - " + todo.getFinishedAt().toString();
            }
            System.out.println(" " + state + " \t" + todo.getTask() + date);
        }
        System.out.println("------------------------");
        System.out.println();

    }

}