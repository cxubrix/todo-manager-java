package lv.rcs.todo.controller.service;

import java.util.List;

import lv.rcs.todo.dto.ToDo;
import lv.rcs.todo.dto.UserDetails;

public interface ToDoManager {

	// CRUD - in user context, ADD todo
	int add(UserDetails user, ToDo todo);

	List<ToDo> getAll(UserDetails user);

	ToDo get(UserDetails user, int id);

	void update(UserDetails user, ToDo todo);

	void remove(UserDetails user, ToDo todo);

	void removeAll(UserDetails user);

}