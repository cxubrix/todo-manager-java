package lv.rcs.todo.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
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
		// user exists in map??
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

	public void addAll(UserDetails user, List<ToDo> todos) {
		for (ToDo todo : todos) {
			add(user, todo);
		}
	}

	@Override
	public List<ToDo> getAll(UserDetails user) {
		return todos.get(user);
	}

	@Override
	public ToDo get(UserDetails user, int id) {
		return getById(user, id);
	}

	@Override
	public void update(UserDetails user, ToDo todo) {
		ToDo original = getById(user, todo.getId());
		if (original == null) {
			return;
		}
		original.setTask(todo.getTask());
		original.setDone(todo.isDone());
		
	}

	@Override
	public void remove(UserDetails user, ToDo todo) {
		List<ToDo> usersTodos = todos.get(user);
		if (usersTodos == null) {
			return;
		}

		if (usersTodos.contains(todo)) {
			usersTodos.remove(todo);
		}

		// make it work!!
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

	private ToDo getById(UserDetails user, int id) {
		List<ToDo> usersTodos = todos.get(user);
		if (usersTodos != null) {
			for (ToDo todo : usersTodos) {
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
