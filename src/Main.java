import java.util.List;

public class Main {

	public static void main(String[] args) {

		// app
		AppController appController = SimpleAppController.getInstance();

		UserDetails user = null;
		try {
			user = appController.signup("test@rcs.lv", "Kaspars", "Test");
		} catch (SignUpException e) {
			System.err.println("Error: " + e.getMessage());
		}

		// separate todo controller for each user
		ToDoController todoController = appController.login(user.getEmail());

		int id0 = todoController.add("Review projects");
		int id1 = todoController.add("Test app");
		int id2 = todoController.add("Check gradle");
		todoController.add("Get some food");
		todoController.add("Drink more water!");

		listToDos(todoController.getMyToDos());

		todoController.deleteToDo(id0);
		todoController.changeStatus(id1, true);
		todoController.updateToDoDescription(id2, "Upload presentation");

		listToDos(todoController.getMyToDos());

	}

	private static void listToDos(List<ToDo> myToDos) {
		System.out.println("-- My ToDo list (" + myToDos.size() + ")------");
		for (ToDo todo : myToDos) {
			char state = todo.isDone() ? '√' : ' ';
			String date = "";
			if (todo.isDone()) {
				date = " - " + todo.getFinishedAt().toString();
			}
			System.out.println(" " + state + " \t" + todo.getTask() + date);
		}
		System.out.println("------------------------");
		System.out.println();

	}

}