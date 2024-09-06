
public class Node {
	private Martyr data;
	private Node left, right;
	private int height;

	public Node(Martyr data) {
		this.data = data;
		this.height = 1;
	}

	public Martyr getData() {
		return data;
	}

	public void setData(Martyr data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
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
		return "Node [data=" + data + "]";
	}

}
