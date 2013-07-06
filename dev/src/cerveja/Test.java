package cerveja;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<String> lista = new ArrayList<String>();

		String a = new String("a");
		
		lista.add(a);
		lista.add("c");
		lista.add("d");
		lista.add("e");
		lista.add("f");
		lista.add("g");
		
		for(String string : lista){
			System.err.println(string);
		}
		
		lista.remove(a);
	
		for(String string : lista){
			System.err.println(string);
		}
		
	}

}
