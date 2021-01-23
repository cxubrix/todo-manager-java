import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SimpleAppControllerTest {

    // dependencies
    ToDoManager todoManager = new SimpleToDoManager();
    UserDetails user = new UserDetails("info@rcs.lv", "Kaspars", "Test");

    // app
    AppControllor controllor = new SimpleAppController(todoManager, user);

    @Test
    public void addTodo() {
        int id = controllor.add("Test message");
        assertTrue("Id not valid!!", id > 0);  // is id > 0???
        
        List<ToDo> todos = controllor.getMyToDos();
        assertEquals(1, todos.size()); // is list size == 1
        
    }
  


}
