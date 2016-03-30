package test.br.inpe.worldwind.view;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.inpe.util.color.ColorBrewer;


public class ColorBrewerTest {
	private Gson gson;
	private ColorBrewer cb;

	@Before
	public void init() {
		this.gson = new Gson();
		this.cb = new ColorBrewer();
	}

	@Test
	public void getAwtColorsTest() throws FileNotFoundException {
		String file = "C:/workspace/InteractiveDataVisualization/src/br/inpe/worldwind/view/resources/colorbrewer.json";
		BufferedReader br = new BufferedReader(new FileReader(file));

		cb = gson.fromJson(br, ColorBrewer.class);

		Map<String, List<Color>> colors = cb.getAwtColors(cb.getBlues());
		String actual = colors.toString();
		String expected = "{3=[java.awt.Color[r=222,g=235,b=247], java.awt.Color[r=158,g=202,b=225], java.awt.Color[r=49,g=130,b=189]], 4=[java.awt.Color[r=239,g=243,b=255], java.awt.Color[r=189,g=215,b=231], java.awt.Color[r=107,g=174,b=214], java.awt.Color[r=33,g=113,b=181]], 5=[java.awt.Color[r=239,g=243,b=255], java.awt.Color[r=189,g=215,b=231], java.awt.Color[r=107,g=174,b=214], java.awt.Color[r=49,g=130,b=189], java.awt.Color[r=8,g=81,b=156]], 6=[java.awt.Color[r=239,g=243,b=255], java.awt.Color[r=198,g=219,b=239], java.awt.Color[r=158,g=202,b=225], java.awt.Color[r=107,g=174,b=214], java.awt.Color[r=49,g=130,b=189], java.awt.Color[r=8,g=81,b=156]], 7=[java.awt.Color[r=239,g=243,b=255], java.awt.Color[r=198,g=219,b=239], java.awt.Color[r=158,g=202,b=225], java.awt.Color[r=107,g=174,b=214], java.awt.Color[r=66,g=146,b=198], java.awt.Color[r=33,g=113,b=181], java.awt.Color[r=8,g=69,b=148]], 8=[java.awt.Color[r=247,g=251,b=255], java.awt.Color[r=222,g=235,b=247], java.awt.Color[r=198,g=219,b=239], java.awt.Color[r=158,g=202,b=225], java.awt.Color[r=107,g=174,b=214], java.awt.Color[r=66,g=146,b=198], java.awt.Color[r=33,g=113,b=181], java.awt.Color[r=8,g=69,b=148]], 9=[java.awt.Color[r=247,g=251,b=255], java.awt.Color[r=222,g=235,b=247], java.awt.Color[r=198,g=219,b=239], java.awt.Color[r=158,g=202,b=225], java.awt.Color[r=107,g=174,b=214], java.awt.Color[r=66,g=146,b=198], java.awt.Color[r=33,g=113,b=181], java.awt.Color[r=8,g=81,b=156], java.awt.Color[r=8,g=48,b=107]]}";

		Assert.assertEquals(expected, actual);
	}

	@After
	public void destroy() {
		this.gson = null;
		this.cb = null;
	}

}
