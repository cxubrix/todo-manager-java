package lv.rcs.todo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiParam;
import lv.rcs.todo.controller.service.ToDoManager;
import lv.rcs.todo.dto.ToDo;
import lv.rcs.todo.dto.UserDetails;

@RestController
@RequestMapping("todo")
public class ToDoController implements lv.rcs.todo.controller.ToDoController {

	@Autowired
	private ToDoManager toDoManager;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/{id}")
	public ToDo getToDo(@PathVariable(value = "id") int id) {
		return toDoManager.get(currentUser(), id);
	}

	@GetMapping
	@Override
	public List<ToDo> getMyToDos() {
		return toDoManager.getAll(currentUser());
	}

	@PostMapping
	@Override
	public int add(@RequestBody @ApiParam(example = "{\n\t\"description\":\"todo task\"\n} ") String payload) {
		// read todo description from json string(to comply with interface and keep
		// method signature)
		String description = readDescription(payload);

		if (description != null) { // add
			ToDo todo = new ToDo(description);
			return toDoManager.add(currentUser(), todo);
		}

		return 0;
	}

	@DeleteMapping("/{id}")
	public void deleteToDo(@PathVariable(value = "id") int id) {
		ToDo todo = toDoManager.get(currentUser(), id);
		if (todo != null) {
			toDoManager.remove(currentUser(), todo);
		}
	}

	@PatchMapping("/{id}/status")
	public void changeStatus(@PathVariable(value = "id") int id, @RequestParam(value = "done") boolean done) {
		ToDo todo = toDoManager.get(currentUser(), id);
		if (todo != null) {
			todo.setDone(done);
			toDoManager.update(currentUser(), todo);
		}
	}

	@PatchMapping("/{id}")
	public void updateToDoDescription(@PathVariable(value = "id") int id,
			@RequestBody @ApiParam(example = "{\n\t\"description\":\"updated todo task\"\n} ") String payload) {
		String description = readDescription(payload);
		if (description != null) {
			ToDo todo = toDoManager.get(currentUser(), id);
			todo.setTask(description);
			toDoManager.update(currentUser(), todo);
		}
	}

	private UserDetails currentUser() { // used to comply with interface method signature, otherwise can be injected
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	private String readDescription(String payload) { // read 'description' attribute from payload json
		String description = null;
		try {
			ObjectNode node = objectMapper.readValue(payload, ObjectNode.class);
			if (node.hasNonNull("description")) {
				description = node.get("description").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // TODO log
		}
		return description;
	}

}
