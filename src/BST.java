public class BST extends BinaryBaseTree implements BSTIF {
	public Node findNode(Martyr data) {
		return findNode(data, getRoot());
	}

	public Node findNode(Martyr data, Node node) {
		if (node == null) {
			return null;
		}
		if (data.compareTo(node.getData())>0) {
			node = findNode(data, node.getRight());
		} else if (data.compareTo(node.getData())<0) {
			node = findNode(data, node.getLeft());
		}
		return node;
	}

	public void insert(Martyr data) {
		setRoot(insertNode(data, getRoot())) ;
	}

	public Node insertNode(Martyr data, Node node) {
		if (node == null) {
			node = new Node(data);
		} else if (data.compareTo(node.getData())>=0) {
			node.setRight(insertNode(data, node.getRight()));
		} else {
			node.setLeft(insertNode(data, node.getLeft()));
		}
		return node;
	}

	public void delete(Martyr data) {
		setRoot(deleteNode(data, getRoot()));
	}

	public Node deleteNode(Martyr data, Node node) {
		if (node == null) {
			return null;
		} else if (data.compareTo(node.getData())>0) {
			node.setRight(deleteNode(data, node.getRight()));
		} else if (data.compareTo(node.getData())<0) {
			node.setLeft(deleteNode(data, node.getLeft()));
		} else if (node.getLeft() == null && node.getRight() == null) {
			node = null;
		} else if (node.getLeft() == null) {
			node = node.getRight();
		} else if (node.getRight() == null) {
			node = node.getLeft();
		} else {
			deleteNodeWithTwoChildren(node);
		}
		return node;
	}

	public void deleteNodeWithTwoChildren(Node node) {
		Node inOrderSuccessor = findMin(node.getRight());
		node.setData(inOrderSuccessor.getData());
		node.setRight(deleteNode(inOrderSuccessor.getData(),node.getRight()));
	}
//	public void deleteNodeWithTwoChildren(Node node) {
//		Node inOrderSuccessor = findMin(node.getRight());
//		node.setData(inOrderSuccessor.getData());
//		node.setRight(deleteNode(inOrderSuccessor.getData(),inOrderSuccessor));
//	}
	private Node findMin(Node node) {
		if (node == null) {
			return null;
		} else if (node.getLeft() == null) {
			return node;
		} else
			return findMin(node.getLeft());
	}

}
