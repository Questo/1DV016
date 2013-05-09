package a2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main implements A2Main {
	
	private Processing p = new Processing();
	
	private String file;
	private double low, high; // percentile
	private int ioi; // item of interest
	
	private ArrayList<Item> items = new ArrayList<>();

	@Override
	public List<Item> readCSVFile(String filename) {
		try {
			BufferedReader br =
					new BufferedReader(new FileReader(filename));
			
			String line = br.readLine();
			while(line != null) {
				String[] itemArray = line.split(",");
				
				items.add(new Item(
						itemArray[0],
						Double.valueOf(itemArray[1]),
						itemArray[2]));
				
				line = br.readLine();
			}
			br.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		System.out.println("List-size: " + items.size());
		Collections.sort(items, p.getTransactionValueComparator());
		
		for(int i = items.size(); --i >= 0;) {
			System.out.print(items.get(i).getTransactionValue() +"\t");
			System.out.println(items.get(i).getDate());
		}
		
		return items;
	}

//	private Item parseLine(String line) {
//		StringBuilder sb = new StringBuilder();
//		String performer = "", date = "";
//		double value = 0;
//		
//		for(int i = 0; i < line.length(); i++) {
//			if(line.charAt(i) == ',' || i + 2 > line.length()) {
//				if(sb.toString().matches("\\d{4}-\\d{2}-\\d{2}")) // date
//					date = sb.toString();
////				if(sb.toString().length() == 10)
////					date = sb.toString();
//				else if(sb.toString().matches("\\d.*")) // value
//					value = Double.valueOf(sb.toString());
//				else if(sb.toString().matches("\\w.*"))
//					performer = sb.toString();
//				
//				// reset stringbuilder
//				sb = new StringBuilder();
//			} else {
//				sb.append(line.charAt(i));
//			}
//		}
//		
//		System.out.println("Performer: " + performer + " Date: " + date + " Value: " + value);
//		
//		return null;
//	}

	@Override
	public double lowThresholdValue(List<Item> items, A2Processing processing,
			int lowPercentile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double highThresholdValue(List<Item> items, A2Processing processing,
			int highPercentile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printTransactionsList(List<Item> items,
			A2Processing processing, double lowThreshold, double highThreshold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printItemsOfInterest(List<Item> items, int m,
			A2Processing processing, double lowThreshold, double highThreshold) {
		// TODO Auto-generated method stub
		
	}
	
	private void printHelp() {
		System.out.println("Usage: main [OPTION]"
				+ "\n-f --file \t\tSet file to load *CSV* (STRING)"
				+ "\n-pL --percentileLow \t\tSet low value for percentile"
				+ "\n-pH --percentileHigh \t\tSet high value for percentile"
				+ "\n-h --help \t\tThis help page");
		System.exit(1);
	}
	
	private void parseArgs(String[] args) {
		// 3 arguments, FILENAME, PERCENTILES LOW/HIGH AND ITEMS OF INTEREST
		if(args.length < 4 || args[0].matches("--help") || args[0].matches("-h"))
			printHelp();
		
		try {
			for(int i = args.length; i-- >= 0;) {
				if(args[i].matches("-f||--file"))
					file = args[i+1];
				else if(args[i].matches("-pL||--percentileLow"))
					low = Double.valueOf(args[i+1]);
				else if(args[i].matches("-pH||--percentileHigh"))
					high = Double.valueOf(args[i+1]);
				else
					printHelp();
			}
			
		} catch(Exception e1) {
			printHelp();
		}
	}
	
	Main(String[] args) {
		//parseArgs(args);
		file = "/home/mathias/1.csv";
		readCSVFile(file);
	}
	
	public static void main(String[] args) { Main m = new Main(args); }

}
