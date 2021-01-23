import java.util.List;

public class SimpleAppController implements AppControllor {

    private ToDoManager toDoManager;
    private UserDetails user;

    public SimpleAppController(ToDoManager toDoManager, UserDetails user) {
        this.toDoManager = toDoManager;
        this.user = user;
    }

    @Override
    public boolean signup(String email, String firstname, String lastname) throws SignUpException {
        if (email == null) {
            SignUpException exp = new SignUpException("email is null!!");
            throw exp;
        }
        return false;
    }

    @Override
    public boolean login(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateProfile(String firstname, String lastname) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(String email) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ToDo> getMyToDos() {
        return toDoManager.getAll(user);
    }

    @Override
    public int add(String description) {
        ToDo todo = new ToDo(description);
        int id = toDoManager.add(user, todo);
        toDoManager.add(user, todo);

        return id;
    }

    @Override
    public void deleteToDo(int id) {
        ToDo toDelete = findById(id);
        if (toDelete != null) {
            toDoManager.remove(user, toDelete);
        }
    }

    @Override
    public void changeStatus(int id, boolean done) {
        ToDo updatedToDo = findById(id);

        if (updatedToDo != null) {
            updatedToDo.setDone(done);
            toDoManager.update(user, updatedToDo);
        }
    }

    @Override
    public void updateToDoDescription(int id, String description) {

        ToDo updatedToDo = findById(id);

        if (updatedToDo != null) {
            updatedToDo.setTask(description);
            toDoManager.update(user, updatedToDo);
        }
    }

    @Override
    public ToDo getToDo(int id) {
        if (id <= 0) {
            return null;
        }
        return findById(id);
    }

    private ToDo findById(int id) {
        List<ToDo> allItems = toDoManager.getAll(user);

        for (ToDo item : allItems) { // find todo item by id
            if (id == item.getId()) {
                return item;
            }
        }

        return null;

    }

}
