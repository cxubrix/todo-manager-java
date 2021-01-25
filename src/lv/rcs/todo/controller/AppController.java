package lv.rcs.todo.controller;

import lv.rcs.todo.SignUpException;
import lv.rcs.todo.dto.UserDetails;

public interface AppController {

	// Main(main method)
	// |
	// V
	// AppController(SimpleAppController) -> ToDoManager(SimpleToDoManager ->
	// User:List<ToDo>)
	//
	// | UserService | SimpleUserService -> List

	// user methods
	UserDetails signup(String email, String firstname, String lastname) throws SignUpException;

	ToDoController login(String email /* , String password */);

	void updateProfile(String firstname, String lastname);

	void deleteUser(String email);

}
