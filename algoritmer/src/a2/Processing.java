package a2;

import java.util.Comparator;
import java.util.List;

public class Processing implements A2Processing {

	@Override
	public Comparator<A2Item> getTransactionValueComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<A2Item> getTransactionDateComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double percentileValue(List<A2Item> items, int percentile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isOutlier(A2Item item, double thresholdLow,
			double thresholdHigh) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double thresholdDistance(A2Item item, double thresholdLow,
			double thresholdHigh) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparator<A2Item> getThresholdDistanceComparator() {
		// TODO Auto-generated method stub
		return null;
	}

}
