package br.com.inpe.interactivedatavisualization.kinect;

import processing.core.PApplet;

/**
 * This class create all objects on screen, like virtual buttons.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class VisualObjects extends PApplet {
	public VisualObjects() {

	}
	public void colorManager(String color, String where, int A, int B, int C) {
		int option = 0;
		if (color == "CUSTOM")
			option = 0;
		if (color == "WHITE")
			option = 1;
		if (color == "BLUE")
			option = 2;
		if (color == "MAGENTA")
			option = 3;
		if (color == "PURPLE")
			option = 4;
		if (color == "GREEN")
			option = 5;
		if (color == "YELLOW")
			option = 6;
		if (color == "RED")
			option = 7;

		switch (option) {
		case 0:
			if (where == "FILL") {
				noStroke();
				fill(A, B, C);
			} else {
				stroke(A, B, C);
				noFill();
			}
			break;

		case 1:
			if (where == "FILL") {
				noStroke();
				fill(255, 255, 255);
			} else {
				stroke(255, 255, 255);
				noFill();
			}
			break;

		case 2:
			if (where == "FILL") {
				noStroke();
				fill(0, 0, 255);
			} else {
				stroke(0, 0, 255);
				noFill();
			}
			break;

		case 3:
			if (where == "FILL") {
				noStroke();
				fill(255, 0, 255);
			} else {
				stroke(255, 0, 255);
				noFill();
			}
			break;

		case 4:
			if (where == "FILL") {
				noStroke();
				fill(77, 0, 153);
			} else {
				stroke(77, 0, 153);
				noFill();
			}
			break;

		case 5:
			if (where == "FILL") {
				noStroke();
				fill(0, 255, 0);
			} else {
				stroke(0, 255, 0);
				noFill();
			}
			break;

		case 6:
			if (where == "FILL") {
				noStroke();
				fill(255, 255, 0);
			} else {
				stroke(255, 255, 0);
				noFill();
			}
			break;
		case 7:
			if (where == "FILL") {
				noStroke();
				fill(255, 0, 0);
			} else {
				stroke(255, 0, 0);
				noFill();
			}
			break;
		}
	}

	public void titleString(String menssage, int sizeFont, float positionX,
			float positionY, String colorFill, int A, int B, int C) {
		textSize(sizeFont);
		colorManager(colorFill, "FILL", A, B, C);
		text(menssage, positionX, positionY);
	}

	public void buttonEllipse(float w, float h, int size, int option,
			String colorFill, String colorStroke, int A, int B, int C,
			String menssage, String colorMenssage) {
		switch (option) {
		case 0:
			// Normal
			colorManager(colorFill, "FILL", A, B, C);
			ellipse(w, h, size, size);
			colorManager(colorStroke, "STROKE", A, B, C);
			ellipse(w, h, size + 10, size + 10);
			titleString(menssage, (int) (size / 3), w - (size / 2) + 5, h,
					colorMenssage, A, B, C);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", A, B, C);
			ellipse(w, h, size, size);
			colorManager("YELLOW", "STROKE", 0, 0, 0);
			ellipse(w, h, size + 10, size + 10);
		}
	}

	public void buttonTriangle(float wX1, float hY1, float wX2, float hY2,
			float wX3, float hY3, int option, String colorFill, int A, int B,
			int C) {
		switch (option) {
		case 0:
			// Normal
			colorManager(colorFill, "FILL", A, B, C);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0, 80);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", 0, 0, 0);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0);
		}
	}

	public void buttonRectangle(float width, float height, float size,
			int option, String colorFill, int A, int B, int C, String menssage,
			String colorMenssage) {

		switch (option) {
		case 0:
			// Normal
			colorManager(colorFill, "FILL", A, B, C);
			rect(width, height, 2 * size, width);
			titleString(menssage, (int) (size / 3 + size / 2), width,
					((3 * height) / 2) + 10, colorMenssage, A, B, C);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", 0, 0, 0);
			rect(width, height, 2 * size, width);
			titleString(menssage, (int) (size / 3 + size / 2), width,
					((3 * height) / 2) + 10, colorMenssage, A, B, C);
		}
	}

	public void buttonEllipse(float w, float h, int size, int option) {
		switch (option) {
		case 0:
			// Normal
			noStroke();
			fill(255, 0, 0);
			ellipse(w, h, size, size);
			stroke(255, 255, 0, 80);
			noFill();
			ellipse(w, h, size + 10, size + 10);
			break;
		case 1:
			// Shiny
			noStroke();
			fill(255, 255, 0);
			ellipse(w, h, size, size);
			stroke(255, 255, 0);
			noFill();
			ellipse(w, h, size + 10, size + 10);
		}
	}

	public void buttonTriangle(float wX1, float hY1, float wX2, float hY2,
			float wX3, float hY3, int option) {
		switch (option) {
		case 0:
			// Normal
			noStroke();
			fill(255, 0, 0);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0, 80);
			break;
		case 1:
			// Shiny
			noStroke();
			fill(255, 255, 0);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0);
		}
	}

	public void buttonRectangle(float width, float height, float size,
			int option) {

		switch (option) {
		case 0:
			// Normal
			colorManager("BLUE", "FILL", 0, 0, 0);
			rect(width, height, 2 * size, width);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", 0, 0, 0);
			rect(width, height, 2 * size, width);
		}
	}

	public void sequentialButton(int width, int height, int size, int option) {

		switch (option) {
		case 0:
			// Circle button
			buttonEllipse(width, height, size, 0);
			buttonEllipse(width, height + 2 * size, size, 0);
			buttonEllipse(width, height + 4 * size, size, 0);
			break;
		case 1:
			// Shiny circle button
			buttonEllipse(width, height, size, 1);
			buttonEllipse(width, height + 2 * size, size, 1);
			buttonEllipse(width, height + 4 * size, size, 1);
			break;
		}
	}

	public void createJoystick(int x, int y, int size, int option) {

		switch (option) {
		case 0:
			// joystick

			// center
			buttonEllipse(x, y, size, 0);
			// up
			buttonTriangle(x, y - 2 * size, x + size / 2, y - size, x - size
					/ 2, y - size, 0);
			// down
			buttonTriangle(x, y + 2 * size, x - size / 2, y + size, x + size
					/ 2, y + size, 0);
			// right
			buttonTriangle(x + 2 * size, y, x + size, y - size / 2, x + size, y
					+ size / 2, 0);
			// left
			buttonTriangle(x - 2 * size, y, x - size, y - size / 2, x - size, y
					+ size / 2, 0);
			break;

		case 1:
			// center
			buttonEllipse(x, y, size, 0);
			// up
			buttonTriangle(x, y - 2 * size, x + size / 2, y - size, x - size
					/ 2, y - size, 0);
			// down
			buttonTriangle(x, y + 2 * size, x - size / 2, y + size, x + size
					/ 2, y + size, 0);
			break;

		case 2:
			// center
			buttonEllipse(x, y, size, 0);
			// right
			buttonTriangle(x + 2 * size, y, x + size, y - size / 2, x + size, y
					+ size / 2, 0);
			// left
			buttonTriangle(x - 2 * size, y, x - size, y - size / 2, x - size, y
					+ size / 2, 0);
			break;

		case 3:
			// zoom
			// center
			buttonEllipse(x, y - 3 * size, size, 0);
			// up
			buttonTriangle(x, y - 2 * size, x + 2 * size / 2, y - size / 2, x
					- 2 * size / 2, y - size / 2, 0);
			// down
			buttonTriangle(x, y + 2 * size, x - 2 * size / 2, y + size / 2, x
					+ 2 * size / 2, y + size / 2, 0);

		}
	}

	public void createNextButton(int option, int width, int height, int size,
			String side) {
		/**
		 * The Height is variable. Choice some point Y in parts of body.
		 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
		 */
		switch (option) {
		case 0:
			// ellipse
			buttonEllipse(width, height, size, 0);
			break;
		case 1:
			// Full Joystick
			createJoystick(width, height, size, 0);
			break;
		case 2:
			// Center, Up and Down
			createJoystick(width, height, size, 1);
			break;
		case 3:
			// Center, Left and Right
			createJoystick(width, height, size, 2);
			break;
		case 4:
			// Rectangle
			buttonRectangle(width, height, size, 0);
			break;
		case 5:
			// Sequencial
			sequentialButton(width , height, size, 0);
		}
	}
}
