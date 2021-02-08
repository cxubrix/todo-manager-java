package lv.rcs.todo.controller;

import java.util.List;

import lv.rcs.todo.controller.service.ToDoManager;
import lv.rcs.todo.dto.ToDo;
import lv.rcs.todo.dto.UserDetails;

public class SimpleToDoController implements ToDoController {

	private ToDoManager toDoManager;
	private UserDetails user;

	protected SimpleToDoController(ToDoManager toDoManager, UserDetails user) {
		this.toDoManager = toDoManager;
		this.user = user;
	}

	@Override
	public List<ToDo> getMyToDos() {
		return toDoManager.getAll(user);
	}

	@Override
	public int add(String description) {
		ToDo todo = new ToDo(description);
		int id = toDoManager.add(user, todo);
		return id;
	}

	@Override
	public void deleteToDo(int id) {
		ToDo toDelete = findById(id);
		if (toDelete != null) {
			toDoManager.remove(user, toDelete);
		}
	}

	@Override
	public void changeStatus(int id, boolean done) {
		ToDo updatedToDo = findById(id);

		if (updatedToDo != null) {
			updatedToDo.setDone(done);
			toDoManager.update(user, updatedToDo);
		}
	}

	@Override
	public void updateToDoDescription(int id, String description) {

		ToDo updatedToDo = findById(id);

		if (updatedToDo != null) {
			updatedToDo.setTask(description);
			toDoManager.update(user, updatedToDo);
		}
	}

	@Override
	public ToDo getToDo(int id) {
		if (id <= 0) {
			return null;
		}
		return findById(id);
	}

	private ToDo findById(int id) {
		List<ToDo> allItems = toDoManager.getAll(user);

		for (ToDo item : allItems) { // find todo item by id
			if (id == item.getId()) {
				return item;
			}
		}

		return null;

	}

}
