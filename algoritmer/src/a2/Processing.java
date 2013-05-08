package a2;

import java.util.Comparator;
import java.util.List;

public class Processing implements A2Processing {
	
	private Comparator<Item> valueComp, dateComp, thresholdComp;

	@Override
	public Comparator<Item> getTransactionValueComparator() {
		valueComp = new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				double delta = o1.getTransactionValue() - o2.getTransactionValue();
				if(delta > 0) return -1;
				if(delta < 0) return 1;

				return 0;
			}
		};
		
		return valueComp;
	}

	@Override
	public Comparator<Item> getTransactionDateComparator() {
		dateComp = new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				return o1.getPerformer().compareTo(o2.getPerformer());
			}
		};
		return dateComp;
	}

	@Override
	public double percentileValue(List<Item> items, int percentile) {
		if(percentile < 0 || percentile > 100) {
			System.err.println("Invalid input, valid percentile range: 0-100");
			System.exit(1);
		}
		
		// special case
		if(percentile == 100)
			return items.get(items.size()-1).getTransactionValue();
		
		double n = ((percentile*items.size())/100) + 0.5;
		return items.get((int) n).getTransactionValue();
	}

	@Override
	public boolean isOutlier(Item item, double thresholdLow,
			double thresholdHigh) {
		double value = item.getTransactionValue();
		
		if(thresholdLow <= value && value <= thresholdHigh)
			return true;
		
		return false;
	}

	@Override
	public double thresholdDistance(Item item, double thresholdLow,
			double thresholdHigh) {
		// method calls are more expensive than variable assignments?
		double value = item.getTransactionValue();
		
		double d1 = Math.abs(thresholdHigh - value);
		double d2 = Math.abs(thresholdLow - value);
		
		return d1 < d2 ? d1 : d2;
	}

	@Override
	public Comparator<Item> getThresholdDistanceComparator() {
		thresholdComp = new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
//				double delta = thresholdDistance(o1, thresholdLow, thresholdHigh)
//						- thresholdDistance(o2, thresholdLow, thresholdHigh);
				double delta = 0;
				
				if(delta > 0) return -1;
				if(delta < 0) return 1;
				return 0;
			}
		};
		return thresholdComp;
	}

}
