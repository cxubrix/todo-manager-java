package lv.rcs.todo.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lv.rcs.todo.controller.service.ToDoManager;
import lv.rcs.todo.dto.ToDo;

@RestController
@RequestMapping("todo")
@Scope()
public class ToDoController implements lv.rcs.todo.controller.ToDoController {

	private ToDoManager toDoManager;

	public ToDoController(ToDoManager toDoManager) {
		this.toDoManager = toDoManager;
	}

	private ObjectMapper objectMapper;

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@GetMapping("/{id}")
	public ToDo getToDo(@PathVariable(value = "id") int id) {
		WebUserDetails user = getAuthentication();
		return toDoManager.get(user, id);
	}

	@GetMapping
	@Override
	public List<ToDo> getMyToDos() {
		List<ToDo> todos = new ArrayList<ToDo>();
		todos.add(new ToDo("check"));
		todos.add(new ToDo("check2"));
		return todos;
	}

	@PostMapping
	@Override
	public int add(@RequestBody String payload) {
		WebUserDetails user = getAuthentication();
		// Object o = auth.getPrincipal();

		System.out.println("payload: " + payload);
		String description = null;
		try {
			ObjectNode node = objectMapper.readValue(payload, ObjectNode.class);
			if (node.has("description")) {
				description = node.get("description").asText();

			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if (description != null) {
			// toDoManager.add(user, todo)
			ToDo todo = new ToDo(description);
		}
		return 0;
	}

	@DeleteMapping("/{id}")
	public void deleteToDo(@PathVariable(value = "id") int id) {
		System.out.println("delete! Id: " + id);
	}

	@PatchMapping("/{id}/{done}")
	public void changeStatus(@PathVariable(value = "id") int id, @RequestParam(value = "done") boolean done) {
		System.out.println("patch! Id: " + id + ", done: " + done);
	}

	@PatchMapping("/{id}")
	public void updateToDoDescription(@PathVariable(value = "id") int id, String description) {
		System.out.println("patch! Id: " + id + ", description: " + description);
	}

	private WebUserDetails getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return (WebUserDetails) auth.getPrincipal();
		}
		return null;
	}

}
