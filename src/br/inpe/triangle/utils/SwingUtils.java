package br.inpe.triangle.utils;

import java.awt.Component;

import javax.swing.JLabel;

import javafx.event.EventType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

/**
 * common swing utilities
 */
public class SwingUtils {
	private static final Component empty = new JLabel();

	public static java.awt.Color toAwt(final Color color) {
		if (null == color) {
			return null;
		}
		if (color.isOpaque() || color.getOpacity() >= 1d) {
			return new java.awt.Color((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue());
		} else {
			return new java.awt.Color((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue(),
					(float) color.getOpacity());
		}
	}

	public static java.awt.Color[] toAwt(Color... colors) {
		java.awt.Color[] awtColors = new java.awt.Color[colors.length];
		for (int i = 0; i < colors.length; i++) {
			awtColors[i] = toAwt(colors[i]);
		}
		return awtColors;
	}

	public static Color fromAwt(final java.awt.Color color) {
		if (null == color) {
			return null;
		}
		if (color.getAlpha() >= 255) {
			return Color.color(color.getRed() / 255d, color.getGreen() / 255d, color.getBlue() / 255d);
		} else {
			return Color.color(color.getRed() / 255d, color.getGreen() / 255d, color.getBlue() / 255d,
					color.getAlpha() / 255d);
		}
	}

	public static java.awt.event.MouseEvent toAwt(final MouseEvent event) {
		final EventType type = event.getEventType();
		if (null == type) {
			return null;
		}

		final int id;
		if (MouseEvent.MOUSE_MOVED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_MOVED;
		} else if (MouseEvent.MOUSE_DRAGGED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_DRAGGED;
		} else if (MouseEvent.MOUSE_CLICKED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_CLICKED;
		} else if (MouseEvent.MOUSE_PRESSED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_PRESSED;
		} else if (MouseEvent.MOUSE_RELEASED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_RELEASED;
		} else if (MouseEvent.MOUSE_ENTERED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_ENTERED;
		} else if (MouseEvent.MOUSE_EXITED.equals(type)) {
			id = java.awt.event.MouseEvent.MOUSE_EXITED;
		} else {
			return null;
		}

		final int button;
		if (event.isPrimaryButtonDown()) {
			button = java.awt.event.MouseEvent.BUTTON1;
		} else if (event.isMiddleButtonDown()) {
			button = java.awt.event.MouseEvent.BUTTON2;
		} else if (event.isSecondaryButtonDown()) {
			button = java.awt.event.MouseEvent.BUTTON3;
		} else {
			button = java.awt.event.MouseEvent.NOBUTTON;
		}

		final long when = -1;
		final int modifiers = modifiers(event);
		final int x = (int) event.getX();
		final int y = (int) event.getY();
		final int xAbs = Integer.MAX_VALUE;
		final int yAbs = Integer.MAX_VALUE;
		final int count = event.getClickCount();
		boolean popup = event.isPopupTrigger();
		if (MouseEvent.MOUSE_CLICKED.equals(type) && event.isMetaDown()) {
			// mack books use meta flag for right-clicks
			popup = true;
		}
		return new java.awt.event.MouseEvent(empty, id, when, modifiers, x, y, xAbs, yAbs, count, popup, button);
	}

	public static java.awt.event.KeyEvent toAwt(final KeyEvent event) {
		final EventType type = event.getEventType();
		if (null == type) {
			return null;
		}

		final int id;
		if (KeyEvent.KEY_TYPED.equals(type)) {
			id = java.awt.event.KeyEvent.KEY_TYPED;
		} else if (KeyEvent.KEY_PRESSED.equals(type)) {
			id = java.awt.event.KeyEvent.KEY_PRESSED;
		} else if (KeyEvent.KEY_RELEASED.equals(type)) {
			id = java.awt.event.KeyEvent.KEY_RELEASED;
		} else {
			return null;
		}

		final long when = -1;
		final int modifiers = modifiers(event);
		final int keyCode = keyCode(event);
		final String keyValue = event.getCharacter();
		final char keyChar = keyValue != null && !keyValue.isEmpty() ? keyValue.charAt(0) : ' ';
		return new java.awt.event.KeyEvent(empty, id, when, modifiers, keyCode, keyChar);
	}

	public static java.awt.event.KeyEvent toAwt(final ScrollEvent event) {
		final EventType type = event.getEventType();
		if (null == type) {
			return null;
		}

		final long when = -1;
		final int id = java.awt.event.KeyEvent.KEY_PRESSED;
		final int keyCode;
		if (event.getDeltaY() > 0) {
			keyCode = java.awt.event.KeyEvent.VK_PAGE_DOWN;
		} else {
			keyCode = java.awt.event.KeyEvent.VK_PAGE_UP;
		}
		final int modifiers = modifiers(event);
		final char keyChar = ' ';
		return new java.awt.event.KeyEvent(empty, id, when, modifiers, keyCode, keyChar);
	}

	private static int keyCode(final KeyEvent event) {
		if (null == event) {
			return 0;
		}
		switch (event.getCode()) {
		case UP:
			return java.awt.event.KeyEvent.VK_UP;
		case DOWN:
			return java.awt.event.KeyEvent.VK_DOWN;
		case LEFT:
			return java.awt.event.KeyEvent.VK_LEFT;
		case RIGHT:
			return java.awt.event.KeyEvent.VK_RIGHT;

		case PAGE_UP:
			return java.awt.event.KeyEvent.VK_PAGE_UP;
		case PAGE_DOWN:
			return java.awt.event.KeyEvent.VK_PAGE_DOWN;

		case BACK_SPACE:
			return java.awt.event.KeyEvent.VK_BACK_SPACE;
		case DELETE:
			return java.awt.event.KeyEvent.VK_DELETE;

		case ESCAPE:
			return java.awt.event.KeyEvent.VK_ESCAPE;

		default:
			return 0;
		}
	}

	private static int modifiers(final InputEvent event) {
		if (event instanceof MouseEvent) {
			final MouseEvent mouseEvent = (MouseEvent) event;
			int modifiers = 0;
			if (mouseEvent.isAltDown()) {
				modifiers |= java.awt.event.MouseEvent.ALT_MASK;
				modifiers |= java.awt.event.MouseEvent.ALT_DOWN_MASK;
			}
			if (mouseEvent.isControlDown()) {
				modifiers |= java.awt.event.MouseEvent.CTRL_MASK;
				modifiers |= java.awt.event.MouseEvent.CTRL_DOWN_MASK;
			}
			if (mouseEvent.isMetaDown()) {
				modifiers |= java.awt.event.MouseEvent.META_MASK;
				modifiers |= java.awt.event.MouseEvent.META_DOWN_MASK;
			}
			if (mouseEvent.isShiftDown()) {
				modifiers |= java.awt.event.MouseEvent.SHIFT_MASK;
				modifiers |= java.awt.event.MouseEvent.SHIFT_DOWN_MASK;
			}
			if (mouseEvent.isPrimaryButtonDown()) {
				modifiers |= java.awt.event.MouseEvent.BUTTON1_MASK;
			}
			if (mouseEvent.isMiddleButtonDown()) {
				modifiers |= java.awt.event.MouseEvent.BUTTON2_MASK;
			}
			if (mouseEvent.isSecondaryButtonDown()) {
				modifiers |= java.awt.event.MouseEvent.BUTTON3_MASK;
			}
			return modifiers;
		}

		if (event instanceof KeyEvent) {
			final KeyEvent keyEvent = (KeyEvent) event;
			int modifiers = 0;
			if (keyEvent.isAltDown()) {
				modifiers |= java.awt.event.KeyEvent.ALT_MASK;
			}
			if (keyEvent.isControlDown()) {
				modifiers |= java.awt.event.KeyEvent.CTRL_MASK;
			}
			if (keyEvent.isMetaDown()) {
				modifiers |= java.awt.event.KeyEvent.META_MASK;
			}
			if (keyEvent.isShiftDown()) {
				modifiers |= java.awt.event.KeyEvent.SHIFT_MASK;
			}
			return modifiers;
		}

		return 0;
	}

	private SwingUtils() {
	}
}
