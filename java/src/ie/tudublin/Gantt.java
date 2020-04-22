// Student Name: Rianlee Pineda
// Student Number: C18301026

package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {	
	public void settings() {
		size(800, 600);
	}

	public void loadTasks() {
		Table t = loadTable("tasks.csv", "header");

		for(TableRow tr : t.rows()) {
			Task ts = new Task(tr);
			tasks.add(ts);
		}
	}

	public void printTasks() {
		for(Task ts : tasks) {
			println(ts);
		}
	}
	
	public void mousePressed() {
		println("Mouse pressed");	
	}

	public void mouseDragged() {
		println("Mouse dragged");
	}

	public void setup() {
		loadTasks();
		printTasks();
	}
	
	public void draw() {			
		background(0);
		displayTasks();
	}

	public void displayTasks() {
		float Bord = width / 16;
		float txtBord = width / 5;
		textAlign(CENTER, CENTER);
		stroke(255);
		fill(255);

		for(int i = 1; i < 31; i++) {
			float x = map(i, 1, 30, txtBord, width - Bord);
			line(x, Bord, x, height - Bord);
			text(i, x, Bord / 2);
		}

		textAlign(LEFT, CENTER);

		for(Task ts : tasks) {
			float txtX = txtBord / 4;
			float txtY = map(tasks.indexOf(ts), 0, tasks.size(), Bord * 2, height - txtBord);

			text(ts.getTask(), txtX, txtY);
		}
	}

	ArrayList<Task> tasks = new ArrayList<Task>();
}