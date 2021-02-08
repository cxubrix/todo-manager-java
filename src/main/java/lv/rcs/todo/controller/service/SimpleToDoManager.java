package lv.rcs.todo.controller.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import lv.rcs.todo.dto.ToDo;
import lv.rcs.todo.dto.UserDetails;

@Service
public class SimpleToDoManager implements ToDoManager {

	// user0 -> (todo0, todo4...)
	// user1 -> (todo2, todo3...)
	// user2 -> (todo1, todo5...)
	// user3 -> null WONT HAPPEN!!!
	// user4 -> ()

	// K = UserDetails
	// V = List<ToDo>
	private HashMap<UserDetails, List<ToDo>> todos;
	private int ids; // zero is not a valid ID!

	public SimpleToDoManager() {
		todos = new HashMap<UserDetails, List<ToDo>>();
		ids = 0;
	}

	// CRUD - in user context, ADD todo
	@Override
	public int add(UserDetails user, ToDo todo) {
		return addClone(user, todo); // TODO return created resource?
	}

	public void addAll(UserDetails user, List<ToDo> todos) {
		for (ToDo todo : todos) {
			addClone(user, todo); // TODO return created resources?
		}
	}

	@Override
	public ToDo get(UserDetails user, int id) {
		return getById(user, id);
	}

	@Override
	public List<ToDo> getAll(UserDetails user) {
		if (todos.containsKey(user)) {
			return Collections.unmodifiableList(todos.get(user));
		}
		return Collections.unmodifiableList(Collections.emptyList());
	}

	@Override
	public void update(UserDetails user, ToDo todo) {

		ToDo current = getById(user, todo.getId());
		int currentIndex = todos.get(user).indexOf(current);

		ToDo updated = new ToDo(todo.getId(), todo);

		todos.get(user).set(currentIndex, updated);

	}

	@Override
	public void remove(UserDetails user, ToDo todo) {
		ToDo current = getById(user, todo.getId());
		todos.get(user).remove(current);
	}

	@Override
	public void removeAll(UserDetails user) {
		List<ToDo> usersTodos = todos.get(user);
		if (usersTodos == null) {
			return;
		}
		// usersTodos.clear();
		usersTodos = new ArrayList<ToDo>();
	}

	private int addClone(UserDetails user, ToDo todo) {
		// user exists in current map?
		boolean exitingUser = todos.containsKey(user);

		// user has any todos already?? (List exists)
		List<ToDo> usersTodos;
		if (exitingUser) {
			usersTodos = todos.get(user);
		} else {
			usersTodos = new ArrayList<ToDo>();
			todos.put(user, usersTodos);
		}

		int id = nextId(); // get unique ID

		// clone
		ToDo toSave = new ToDo(id, todo);

		// add
		usersTodos.add(toSave);
		return id;
	}

	private ToDo getById(UserDetails user, int id) {
		if (user != null && todos.containsKey(user)) {
			List<ToDo> userTodos = todos.get(user);
			for (ToDo todo : userTodos) {
				if (todo.getId() == id) {
					return todo;
				}
			}
		}
		return null;
	}

	private int nextId() {
		return ++ids;
	}
}
