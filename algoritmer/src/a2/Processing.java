package a2;

import java.util.Comparator;
import java.util.List;

public class Processing implements A2Processing {
	
	private Comparator valueComp, dateComp;

	@Override
	public Comparator<Item> getTransactionValueComparator() {
		valueComp = new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				double delta = o1.getTransactionValue() - o2.getTransactionValue();
				if(delta < 0) return -1;
				if(delta > 0) return 1;
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
		
		if(percentile == 100)
			return items.get(items.size()-1).getTransactionValue();
		
//		if(percentile == 0)
//			return items.get(0).getTransactionValue();
		
		double n = ((percentile*items.size())/100) + 0.5;
		return items.get((int) n).getTransactionValue();
	}

	@Override
	public boolean isOutlier(Item item, double thresholdLow,
			double thresholdHigh) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double thresholdDistance(Item item, double thresholdLow,
			double thresholdHigh) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparator<Item> getThresholdDistanceComparator() {
		// TODO Auto-generated method stub
		return null;
	}

}
