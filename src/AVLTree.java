
public class AVLTree extends BST {

	@Override
	public Node insertNode(Martyr data, Node node) {
		node = super.insertNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}

	@Override
	public Node deleteNode(Martyr data, Node node) {
		node = super.deleteNode(data, node);
		if (node == null) {
		    return null;
		  }
		updateHeight(node);
		return reBalance(node);
	}

	private void updateHeight(Node node) {
		int leftChildHeight = height(node.getLeft());
		int rightChildHeight = height(node.getRight());
		node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);
	}


	public int height(Node node) {
		return node != null ? node.getHeight() : -1;
	}

	private Node reBalance(Node node) {
		int bf = balanceFactor(node);
		if (bf > 1) {
			if (balanceFactor(node.getLeft()) >= 0) {
				node = rightRotation(node);
			} else {
				node.setLeft(leftRotation(node.getLeft()));
				node = rightRotation(node);
			}
		}
		if (bf < -1) {
			if (balanceFactor(node.getRight()) <= 0) {
				node = leftRotation(node);
			} else {
				node.setRight(rightRotation(node.getRight()));
				node = leftRotation(node);
			}
		}
		return node;
	}

	private Node rightRotation(Node node) {
		Node x = node.getLeft();
		node.setLeft(x.getRight());
		x.setRight(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	private Node leftRotation(Node node) {
		Node x = node.getRight();
		node.setRight(x.getLeft());
		x.setLeft(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	private int balanceFactor(Node node) {
		int bf = height(node.getLeft()) - height(node.getRight());
		return bf;
	}

	public int getNodeCount(Node node) {
		if (node == null)
			return 0;
		else
			return 1 + getNodeCount(node.getLeft()) + getNodeCount(node.getRight());
	}

}