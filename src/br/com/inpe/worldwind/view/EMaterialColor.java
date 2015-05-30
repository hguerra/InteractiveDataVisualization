package br.com.inpe.worldwind.view;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public enum EMaterialColor {
	RED, WHITE, YELLOW, GREEN, BLUE;
	
	public String toString() {
		switch (this) {
		case RED:return "Municipalities with an area less than 300 KM";
		case WHITE:return "Municipalities with an area between: 300 and 500 KM";
		case YELLOW:return "Municipalities with an area between: 500 and 800 KM";
		case GREEN:return "Municipalities with an area between: 800 and 1000 KM";
		case BLUE:return "Municipalities with an area greater than 1000KM";
		default:return "NOT FOUND";
		}
	};
}
