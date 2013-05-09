package a2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main implements A2Main {
	
	private Processing p = new Processing();
	
	private String file;
	private double low, high;
	
	private int hPercentile, lPercentile;
	private int ioi;
	
	
	private ArrayList<Item> items = new ArrayList<>();

	@Override
	public List<Item> readCSVFile(String filename) {
		try {
			BufferedReader br =
					new BufferedReader(new FileReader(filename));
			
			String line = br.readLine();
			while(line != null && line.length() != 0) {
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
		
		Collections.sort(items, p.getTransactionValueComparator());
		
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
	public double lowThresholdValue(List<Item> items, Processing processing,
			int lowPercentile) {
		return processing.percentileValue(items, lowPercentile);
	}

	@Override
	public double highThresholdValue(List<Item> items, Processing processing,
			int highPercentile) {
		return processing.percentileValue(items, highPercentile);
	}

	@Override
	public void printTransactionsList(List<Item> items,
			Processing processing, double lowThreshold, double highThreshold) {
		Collections.sort(items, processing.getTransactionDateComparator());
		
		System.out.println("TRANSACTIONS:");
		for(int i = 0; i < items.size(); i++) {
			if(processing.isOutlier(items.get(i), lowThreshold, highThreshold))
				System.out.println(items.get(i).toString() + "\t ( ¯\\_(ツ)_/¯ )");
			else
				System.out.println(items.get(i).toString());
		}
	}

	@Override
	public void printItemsOfInterest(List<Item> items, int m,
			Processing processing, double lowThreshold, double highThreshold) {
		PriorityQueue<Item> pq = new PriorityQueue<>(items.size(),
				processing.getThresholdDistanceComparator());
		
		// find all non-outlier items
		System.out.println("\nITEMS OF INTEREST:");
		for(int i = 0; i < items.size(); i++) {
			if(!processing.isOutlier(items.get(i), lowThreshold, highThreshold))
				pq.add(items.get(i));
		}
		
		m = (pq.size() < m) ? pq.size() : m; 
		for(int i = 0; i < m; i++) {
			System.out.println(pq.remove().toString());
		}
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
		Scanner sc = new Scanner(System.in);
		
		if(args.length < 4) {
			System.out.print("Path to file: ");
			file = sc.nextLine();
			
			System.out.print("Percentiles: ");
			int d1 = sc.nextInt();
			int d2 = sc.nextInt();
			
			if(d1 > d2) {
				hPercentile = d1; lPercentile = d2;
			} else {
				hPercentile = d2; lPercentile = d1;
			}
			
			System.out.print("Number of items of interest: ");
			ioi = sc.nextInt();
				
		}
		else {
			file = args[0];
			if(Integer.valueOf(args[1]) > Integer.valueOf(args[2])) {
				hPercentile = Integer.valueOf(args[1]); 
				lPercentile = Integer.valueOf(args[2]);
			} else {
				hPercentile = Integer.valueOf(args[2]);
				lPercentile = Integer.valueOf(args[1]);
			}
			ioi = Integer.valueOf(args[3]);
		}
	}
	
	Main(String[] args) {
		parseArgs(args);
		
		//file = "/home/mathias/1.csv";
		readCSVFile(file);
		low = lowThresholdValue(items, p, lPercentile);
		high = highThresholdValue(items, p, hPercentile);
		
		p.thresholdLow = low;
		p.thresholdHigh = high;
		
		printTransactionsList(items, p, low, high);
		printItemsOfInterest(items, ioi, p, low, high);
	}
	
	public static void main(String[] args) { Main m = new Main(args); }

}
