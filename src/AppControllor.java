import java.util.List;

public interface AppControllor {

    // Main(main method)
    //  |
    //  V
    // AppController(SimpleAppController) -> ToDoManager(SimpleToDoManager -> User:List<ToDo>)
    //
    // | UserService | SimpleUserService -> List
    
    
    // user methods
    boolean signup(String email, String firstname, String lastname) throws SignUpException;
    
    boolean login(String email /* , String password */);
    
    void updateProfile(String firstname, String lastname);
    
    void deleteUser(String email);
    
    // todos
    ToDo getToDo(int id);
    
    List<ToDo> getMyToDos();
    
    int add(String description);
    
    void deleteToDo(int id);
    
    void changeStatus(int id, boolean done);
    
    void updateToDoDescription(int id, String description);

    
}
