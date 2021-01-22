import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserService();

        UserDetails dummy = new UserDetails("kaspars@rcs.lv", "Kaspars", "RCS");

        // C
        userService.save(dummy);

        // R
        UserDetails dummyUserFromStorage = userService.get("kaspars@rcs.lv");

        System.out.println("Expected 'Kaspars' " + dummyUserFromStorage.getFirstname()); // not working???
        boolean passed = ("Kaspars".equals(dummyUserFromStorage.getFirstname()));
        System.out.println("Test passed: " + passed);

        UserDetails fakeUser = userService.get("fake@email.com");
        if (fakeUser == null) {
            System.out.println("user not found!!");
        } else {
            fakeUser.getFirstname();
        }

        dummyUserFromStorage.setFirstname("RememberMe");
        dummyUserFromStorage.setLastname("Test");
        userService.update(dummyUserFromStorage); // U

        userService.remove("kaspars@rcs.lv"); // D

        System.out.println("DONE!");

       ToDoManager manager = new ToDoManager();
       List<ToDo> myItems =  manager.getAll(dummy);
       ToDo todo = myItems.get(0);
       // todo.setDone();
       manager.update(dummy, todo);
       
    }

  
}
