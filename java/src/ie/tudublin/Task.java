// Student Name: Rianlee Pineda
// Student Number: C18301026

package ie.tudublin;

import processing.data.TableRow;

public class Task {
	private String task;
	private int start, end;
	private boolean gotStart, gotEnd;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean getGotStart() {
		return gotStart;
	}

	public void setGotStart(boolean gotStart) {
		this.gotStart = gotStart;
	}

	public boolean getGotEnd() {
		return gotEnd;
	}

	public void setGotEnd(boolean gotEnd) {
		this.gotEnd = gotEnd;
	}

	public Task(String task, int start, int end) {
		this.task = task;
		this.start = start;
		this.end = end;
		this.gotStart = false;
		this.gotEnd = false;
	}

	public Task(TableRow tr) {
		this(
			tr.getString("Task"),
			tr.getInt("Start"),
			tr.getInt("End")
		);
		this.gotStart = false;
		this.gotEnd = false;
	}

	public String toString() {
		return task + "\t" + start + "\t" + end;
	}
}