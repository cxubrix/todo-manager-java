import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // dependencies
        ToDoManager todoManager = new SimpleToDoManager();
        UserDetails user = new UserDetails("info@rcs.lv", "Kaspars", "Test");
        
        // app
        AppControllor controllor = new SimpleAppController(todoManager, user);
        try {
            boolean result = controllor.signup(null, "", "");
        } catch (SignUpException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        
        File file = new File("test.txt");

        // try this code
        // catch errors
        // always execute

        // other code
        Scanner sc = null;
        try {
            FileInputStream in = new FileInputStream(file);
            sc = new Scanner(System.in);

            // ok
            // work with file if possible

        } catch (FileNotFoundException e) {
            // called only when exception is thrown
            System.out.println("File with name " + file.getName() + " not found!");
        } finally {
            System.out.println("Close your scanner");
            if (sc == null) {
                sc.close();
            }
        }

        System.out.println("Done with result = GREAT");

    }

}