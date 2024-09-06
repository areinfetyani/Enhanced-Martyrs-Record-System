
public class AVLTree2 extends BST2 {

	@Override
	public Node2 insertNode(DateStack data, Node2 node) {
		node = super.insertNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}

	@Override
	public Node2 deleteNode(DateStack data, Node2 node) {
		node = super.deleteNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}

	private void updateHeight(Node2 node) {
		int leftChildHeight = height(node.getLeft());
		int rightChildHeight = height(node.getRight());
		node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);

	}

	public int height(Node2 node) {
		return node != null ? node.getHeight() : -1;
	}

	private Node2 reBalance(Node2 node) {
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

	private Node2 rightRotation(Node2 node) {
		Node2 x = node.getLeft();
		node.setLeft(x.getRight());
		x.setRight(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	private Node2 leftRotation(Node2 node) {
		Node2 x = node.getRight();
		node.setRight(x.getLeft());
		x.setLeft(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	private int balanceFactor(Node2 node) {
		int bf = height(node.getLeft()) - height(node.getRight());
		return bf;
	}

}