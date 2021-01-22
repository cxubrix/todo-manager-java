import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToDoManager {

    // user0 -> (todo, todo...)
    // user1 -> (todo, todo...)
    // user2 -> (todo, todo...)
    // user3 -> null WONT HAPPEN!!!
    // user4 -> ()

    // K = UserDetails
    // V = List<ToDo>
    private HashMap<UserDetails, List<ToDo>> todos;

    public ToDoManager() {
        todos = new HashMap<UserDetails, List<ToDo>>();
    }

    // CRUD - in user context, ADD todo
    public void add(UserDetails user, ToDo todo) {
        // user exists in map??
        boolean exitingUser = todos.containsKey(user);

        // user has any todos already?? (List exists)
        List<ToDo> usersTodos;
        if (exitingUser) {
            usersTodos = todos.get(user);
        } else {
            usersTodos = new ArrayList<ToDo>();
            todos.put(user, usersTodos);
        }

        usersTodos.add(todo);
    }

    public void addAll(UserDetails user, List<ToDo> todos) {
        for (ToDo todo : todos) {
            add(user, todo);
        }
    }

    public List<ToDo> getAll(UserDetails user) {
        return todos.get(user);
    }

    public void update(UserDetails user, ToDo todo) {
        List<ToDo> usersTodos = todos.get(user);
        if (usersTodos == null) {
            return;
        }
        // user has todos
        // find single todo to update
        // make it work!!
    }

    public void remove(UserDetails user, ToDo todo) {
        List<ToDo> usersTodos = todos.get(user);
        if (usersTodos == null) {
            return;
        }

        if (usersTodos.contains(todo)) {
            usersTodos.remove(todo);
        }

        // make it work!!
    }

    public void removeAll(UserDetails user) {
        List<ToDo> usersTodos = todos.get(user);
        if (usersTodos == null) {
            return;
        }
        // usersTodos.clear();
        usersTodos = new ArrayList<ToDo>();
    }
}
