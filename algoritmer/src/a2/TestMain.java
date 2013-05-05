package a2;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMain {

	public static void main(String[] args) {
		ArrayList<Item> a = new ArrayList<>();
		
		a.add(new Item("john", "2011-02-21", 4.4));
		a.add(new Item("albert", "2010-08-13", 16.1));
		a.add(new Item("zack", "2009-10-05", 18.3));
		a.add(new Item("bob", "2012-04-17", 22.5));
		
		Processing p = new Processing();
		
		
		System.out.println(p.percentileValue(a, 0));

	}

}
