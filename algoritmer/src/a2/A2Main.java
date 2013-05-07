package a2;

import java.util.List;

public interface A2Main {
	public List<Item> readCSVFile(String filename);
	public double lowThresholdValue(List<Item> items,
			A2Processing processing, int lowPercentile);
	public double highThresholdValue(List<Item> items,
			A2Processing processing, int highPercentile);
	public void printTransactionsList(List<Item> items,
			A2Processing processing, double lowThreshold, double highThreshold);
	public void printItemsOfInterest(final List<Item> items, int m,
			A2Processing processing, double lowThreshold, double highThreshold);
}
