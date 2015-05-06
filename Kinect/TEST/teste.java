package TEST;

import java.util.ArrayList;
import java.util.List;

public class teste {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		Timer t= new Timer();
		a.add("A");
		a.add("B");
		a.add("C");
		a.add("D");
		a.add("E");
		int i =0;
		int s = 1;
		
		//Imprime um elemento por segundo =D
		t.startCounter();
		while (i<a.size()){
			if(t.endCounterSeconds(s)){
				System.out.println(a.get(i));
				i++;
				s++;
			}
		}

	}

}
