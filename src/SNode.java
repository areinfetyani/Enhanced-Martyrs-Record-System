
public class SNode {
	private Object element;
	private SNode next;

	public SNode(Object element) {
		this.element = element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public SNode getNext() {
		return next;
	}

	public void setNext(SNode next) {
		this.next = next;
	}


	@Override
	public String toString() {
		return element.toString();
	}

}