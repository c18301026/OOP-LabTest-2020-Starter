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
		float Bord = width / 16;
		float txtBord = width / 5;
		float taskH = Bord / 1.5f;
		float slideW = 10;

		for(Task ts : tasks) {
			float startX = map(ts.getStart(), 1, 30, txtBord, width - Bord);
			float endX = map(ts.getEnd(), 1, 30, txtBord, width - Bord);
			float y = map(tasks.indexOf(ts), 0, tasks.size(), Bord * 2, height - txtBord);

			stroke(255);
			fill(0);

			if(
				((mouseX > startX) && (mouseX < startX + slideW)) && 
				(mouseY > (y - (taskH / 2))) && (mouseY < (y + (taskH / 2)))
			) {
				ts.setGotStart(true);
			}
			if(
				((mouseX < endX) && (mouseX > endX - slideW)) && 
				(mouseY > (y - (taskH / 2))) && (mouseY < (y + (taskH / 2)))
			) {
				ts.setGotEnd(true);
			}
		}
	}

	public void mouseReleased() {
		for(Task ts : tasks) {
			ts.setGotStart(false);
			ts.setGotEnd(false);
		}
	}

	public void mouseDragged() {
		float Bord = width / 16;
		float txtBord = width / 5;

		for(Task ts : tasks) {
			float x = map(mouseX, txtBord, width - Bord, 1, 30);

			if(ts.getGotStart()) {
				if(((int) x < ts.getEnd()) && (x > 1)) ts.setStart((int) x);
			}
			if(ts.getGotEnd()) {
				if(((int) x > ts.getStart()) && (x < 31)) ts.setEnd((int) x);
			}
		}
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
		colorMode(HSB);
		rectMode(CORNERS);

		for(int i = 1; i < 31; i++) {
			float x = map(i, 1, 30, txtBord, width - Bord);
			line(x, Bord, x, height - Bord);
			text(i, x, Bord / 2);
		}

		textAlign(LEFT, CENTER);
		noStroke();

		for(Task ts : tasks) {
			float txtX = txtBord / 4;
			float y = map(tasks.indexOf(ts), 0, tasks.size(), Bord * 2, height - txtBord);
			float taskX = map(ts.getStart(), 1, 30, txtBord, width - Bord);
			float taskH = Bord / 1.5f;
			float taskEnd = map(ts.getEnd(), 1, 30, txtBord, width - Bord);
			float hue = map(tasks.indexOf(ts), 0, tasks.size(), 0, 255);

			fill(hue, 255, 255);
			rect(taskX, y - (taskH / 2), taskEnd, y + (taskH / 2), 5);

			fill(255);
			text(ts.getTask(), txtX, y);
		}
	}

	ArrayList<Task> tasks = new ArrayList<Task>();
}