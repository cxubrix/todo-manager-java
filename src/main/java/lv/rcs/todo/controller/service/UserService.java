package lv.rcs.todo.controller.service;

import lv.rcs.todo.dto.UserDetails;

public interface UserService {

	// Create - create persistent entity in storage for create object
	void save(UserDetails userDetails);

	// Read - read "unique" user which is identified by id(email for now)
	UserDetails get(String id);

	// Update,
	void update(UserDetails userDetails);

	// Delete
	void remove(String id);

}