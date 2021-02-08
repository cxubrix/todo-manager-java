package lv.rcs.todo.dto;

import java.util.Date;

public class ToDo { // dto

	private int id;
	// task √ (2021.01.01)
	private String task; // go out
	private boolean done; // false
	private Date finishedAt; // 2021
//     private Date createdAt;
	// private Date lastEditedAt;
	// private boolean deleted;

	// one to many
	// one user can have many todos
	// user1 ->()
	// user2 ->(todo0, todo2)
	// user3 ->(todo1, todo4, todo3)

	// LIST 0, 1, 2, 3

	// MAP key : value

	public ToDo(String task) {
		this.task = task;
	}

	public ToDo(int id, ToDo original) {
		this.id = id;
		this.task = original.getTask();
		this.done = original.isDone();
		this.finishedAt = original.finishedAt;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
		if (done) {
			finishedAt = new Date();
		} else {
			finishedAt = null;
		}
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", task=" + task + ", done=" + done + ", finishedAt=" + finishedAt + "]";
	}

}
