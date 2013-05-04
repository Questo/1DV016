package a2;

import java.util.Comparator;
import java.util.List;

public interface A2Processing {
	public Comparator<A2Item> getTransactionValueComparator();
	public Comparator<A2Item> getTransactionDateComparator();
	
	public double percentileValue(List<A2Item> items, int percentile);
	
	public boolean isOutlier(A2Item item, double thresholdLow, double thresholdHigh);
	public double thresholdDistance(A2Item item, double thresholdLow, double thresholdHigh);
	public Comparator<A2Item> getThresholdDistanceComparator();
}
