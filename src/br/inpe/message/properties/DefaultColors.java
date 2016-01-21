package br.inpe.message.properties;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class DefaultColors {
	public static final Color c1 = new Color(0, 100, 1);// Color.decode("#006401");
	public static final Color c2 = new Color(56, 130, 53);// 388237
	public static final Color c3 = new Color(113, 159, 113);// 719F71
	public static final Color c4 = new Color(170, 189, 169);// AABDA9
	public static final Color c5 = new Color(213, 206, 196);// D6CDC4
	public static final Color c6 = new Color(225, 187, 140);// E1BB8C
	public static final Color c7 = new Color(236, 168, 83);// ECA853
	public static final Color c8 = new Color(249, 148, 30);// F9941E
	public static final Color c9 = new Color(240, 121, 1);// F07A00
	public static final Color c10 = new Color(212, 84, 0);// D15400
	public static final Color c11 = new Color(178, 48, 0);// B23000
	public static final Color c12 = new Color(147, 9, 0);// 930900
	public static final Color c13 = new Color(161, 51, 50);// A13332
	public static final Color c14 = new Color(193, 119, 120);// C17778
	public static final Color c15 = new Color(223, 187, 187);// DFBBBB
	public static final Color cNegative127 = Color.decode("#000000");
	// c1, c2, c9, c10, c12, c3, Color.black
	
	public static Color[] getOriginalColors1() {
		Color[] colors = { c1, c2, c9, c10, c12, c3, cNegative127 };
		return colors;
	}
	public static Color[] getOriginalColors2() {
		Color[] colors = { c1, c2, c9, c10, c11, c12, c3, cNegative127 };
		return colors;
	}

	public static Map<Integer, Color> getDefaultColors(){
		Map<Integer, Color> map = new HashMap<>();
		map.put(1, c1);
		map.put(2, c2);
		map.put(9, c9);
		map.put(10, c10);
		map.put(11, c11);
		map.put(12, c12);
		map.put(3, c3);
		map.put(-127, cNegative127);
		return map;
	}
}
