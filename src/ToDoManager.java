import java.util.List;

public interface ToDoManager {

    // CRUD - in user context, ADD todo
    int add(UserDetails user, ToDo todo);

    List<ToDo> getAll(UserDetails user);

    void update(UserDetails user, ToDo todo);

    void remove(UserDetails user, ToDo todo);

    void removeAll(UserDetails user);

}