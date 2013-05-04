package a2;

public class Item implements A2Item {
	
	private Node n;

	@Override
	public String getPerformer() { return n.performer; }

	@Override
	public double getTransactionValue() { return n.value; }

	@Override
	public String getDate() { return n.date; }

	@Override
	public void setPerformer(String name) { n.performer = name; }

	@Override
	public void setTransactionValue(double value) { n.value = value; }

	@Override
	public void setDate(String date) { n.date = date; }

}

class Node {
	
	public String performer, date;
	public double value;
	
	public Node next;
	
	public Node(String performer, String date, double value) {
		this(performer, date, value, null);
	}

	public Node(String performer, String date, double value, Node next) {
		this.performer = performer; this.date = date;
		this.value = value;
		this.next = next;
	}
}
