import java.util.Date;

public class DateStack implements Comparable<DateStack> {
	Date date;
	Stack stack;

	public DateStack(Date date) {
		this.date = date;
		stack = new Stack();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Stack getStack() {
		return stack;
	}

	public void setStack(Martyr obj) {
		stack.push(obj);
		this.stack = stack;
	}

	@Override
	public int compareTo(DateStack o) {
		return this.getDate().compareTo(o.getDate());
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(getDate().equals(((DateStack)obj).getDate()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "DateStack [date=" + date + ", stack=" + stack + "]";
	}
	
}
