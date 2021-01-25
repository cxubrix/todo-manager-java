import java.util.List;

public interface ToDoController {
	
    ToDo getToDo(int id);
    
    List<ToDo> getMyToDos();
    
    int add(String description);
    
    void deleteToDo(int id);
    
    void changeStatus(int id, boolean done);
    
    void updateToDoDescription(int id, String description);

}
