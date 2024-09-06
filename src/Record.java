import java.util.Date;

public class Record implements Comparable<Record> {
	private String location;
	private AVLTree names;
	private AVLTree2 dates;

	public Record(String location) {
		this.location = location;
		names = new AVLTree();
		dates = new AVLTree2();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public AVLTree getNames() {
		return names;
	}

	public void setNames(AVLTree names, Martyr obj) {
		names.insert(obj);
		this.names = names;
	}
	public void setNames(AVLTree names) {
		this.names = names;
	}
	public AVLTree2 getDates() {
		return dates;
	}

	public void setDates(AVLTree2 dates, DateStack obj) {
		dates.insert(obj);
		this.dates = dates;
	}
	public void setDates(AVLTree2 dates) {
		this.dates = dates;
	}
	@Override
	public int compareTo(Record o) {
		return this.getLocation().compareTo(o.getLocation());
	}

	@Override
	public String toString() {
		return "Record [location=" + location + "]";
	}

}
