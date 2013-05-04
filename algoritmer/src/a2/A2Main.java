package a2;

import java.util.List;

public interface A2Main {
	public List<A2Item> readCSVFile(String filename);
	public double lowThresholdValue(A2Processing processing, int lowPercentile);
	public double highThresholdValue(A2Processing processing, int highPercentile);
	public void printTransactionsList(List<A2Item> items,
			A2Processing processing, double lowThreshold, double highThreshold);
	public void printItemsOfInterest(final List<A2Item> items, int m,
			A2Processing processing, double lowThreshold, double highThreshold);
}
