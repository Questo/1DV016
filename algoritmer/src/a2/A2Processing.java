package a2;

import java.util.Comparator;
import java.util.List;

public interface A2Processing {
	public Comparator<Item> getTransactionValueComparator();
	public Comparator<Item> getTransactionDateComparator();
	
	public double percentileValue(List<Item> items, int percentile);
	
	public boolean isOutlier(Item item, double thresholdLow, double thresholdHigh);
	public double thresholdDistance(Item item, double thresholdLow, double thresholdHigh);
	public Comparator<Item> getThresholdDistanceComparator();
}
