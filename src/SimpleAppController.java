import java.util.List;

public class SimpleAppController implements AppController {

	private static AppController instance;
	private ToDoManager toDoManager;
	private UserService userService;

	private SimpleAppController() {
		this(new SimpleUserService(), new SimpleToDoManager());
	}

	protected SimpleAppController(UserService userService, ToDoManager toDoManager) {
		this.userService = userService;
		this.toDoManager = toDoManager;
	}

	@Override
	public UserDetails signup(String email, String firstname, String lastname) throws SignUpException {
		if (email == null) {
			throw new SignUpException("email is null");
		}

		if (userService.get(email) != null) {
			throw new SignUpException("user with this email already exists");
		}

		UserDetails user = new UserDetails(email, firstname, lastname);
		userService.save(user);
		return userService.get(email);
	}

	@Override
	public ToDoController login(String email) {

		UserDetails user = new UserDetails(email);
		return new SimpleToDoController(toDoManager, user);
	}

	@Override
	public void updateProfile(String firstname, String lastname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String email) {

	}

	public static AppController getInstance() { // lazy init singleton
		if (instance == null) {
			instance = new SimpleAppController();
		}
		return instance;
	}

}
