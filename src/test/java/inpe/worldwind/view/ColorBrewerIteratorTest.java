package inpe.worldwind.view;

import br.inpe.util.color.ColorBrewer;
import br.inpe.util.color.ColorBrewerName;
import br.inpe.worldwind.view.controllers.ManagerSetupController;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ColorBrewerIteratorTest {

	public static void main(String[] args) {
		ColorBrewer colorBrewer = ManagerSetupController.getInstance().getColorBrewer();
		Map<Integer, List<Color>> colors = colorBrewer.getAwtColors(colorBrewer.getValue(ColorBrewerName.YlGn));

		Entry<Integer, List<Color>> firstElement7 = colors.entrySet().iterator().next();
		
		System.out.println(firstElement7.getKey());
		System.out.println(firstElement7.getValue());
		
		Integer lastElement7 = (Integer) colors.keySet().toArray()[colors.size()-1];
		System.out.println(lastElement7);
		
		/*java8*/
		
		Entry<Integer, List<Color>> firstElement = colors.entrySet().stream().iterator().next();
		System.out.println(firstElement.getKey());
		System.out.println(firstElement.getValue());
		
		Integer lastElement = colors.keySet().stream().mapToInt(last -> last.intValue()).toArray()[colors.size()-1];
		System.out.println(lastElement);
	}

}
