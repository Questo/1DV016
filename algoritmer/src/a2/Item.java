package a2;

public class Item implements A2Item {
	
	private String performer, date;
	private double value;
	
	public Item(String performer, double value, String date) {
		this.performer = performer; this.date = date;
		this.value = value;
	}

	@Override
	public String getPerformer() { return performer; }

	@Override
	public double getTransactionValue() { return value; }

	@Override
	public String getDate() { return date; }

	@Override
	public void setPerformer(String name) { performer = name; }

	@Override
	public void setTransactionValue(double value) { this.value = value; }

	@Override
	public void setDate(String date) { this.date = date; }

}

