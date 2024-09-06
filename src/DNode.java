
public class DNode {
	private Record record;
	private DNode prev, next;
	
	public DNode(String location) {
		record = new Record(location);
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}


	@Override
	public String toString() {
		return  record.toString();
	}
	
	
}
