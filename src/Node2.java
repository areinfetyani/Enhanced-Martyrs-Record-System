
public class Node2 {
	private DateStack data;
	private Node2 left, right;
	private int height;

	public Node2(DateStack data) {
		this.data = data;
		this.height = 1;
	}

	public DateStack getData() {
		return data;
	}

	public void setData(DateStack data) {
		this.data = data;
	}

	public Node2 getLeft() {
		return left;
	}

	public void setLeft(Node2 left) {
		this.left = left;
	}

	public Node2 getRight() {
		return right;
	}

	public void setRight(Node2 right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Node2 [data=" + data + "]";
	}

}
