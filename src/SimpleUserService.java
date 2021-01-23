import java.util.ArrayList;
import java.util.List;

public class SimpleUserService implements UserService {

    // CRUD - Create, Read, Update, Delete
    private List<UserDetails> users;

    public SimpleUserService() {
        users = new ArrayList<UserDetails>();
    }

    // Create - create persistent entity in storage for create object
    @Override
    public void save(UserDetails userDetails) {
        users.add(userDetails);
    }

    // Read - read "unique" user which is identified by id(email for now)
    @Override
    public UserDetails get(String id) {
        for (UserDetails user : users) {
            String email = user.getEmail();
            if (email.equals(id)) { // id matches email?
                return user;
            }
        }
        return null;
    }

    // Update,
    @Override
    public void update(UserDetails userDetails) {
        String emailToFind = userDetails.getEmail();
          
        
        for (int i = 0; i < users.size(); i++) {
            // 0, 1, 2...
            String currentUserEmail = users.get(i).getEmail();
            if (currentUserEmail.equals(emailToFind)) { // find index
                users.set(i, userDetails);  // set value if found
                break;
            }
        }
    }

    // Delete
    @Override
    public void remove(String id) {
        String emailToFind = id;
        
        for (int i = 0; i < users.size(); i++) {
            // 0, 1, 2...
            String currentUserEmail = users.get(i).getEmail();
            if (currentUserEmail.equals(emailToFind)) { // find index
                users.remove(i);
                break;
            }
        }  
    }

}
