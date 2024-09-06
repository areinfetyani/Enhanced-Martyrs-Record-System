public class BST2 extends BinaryBaseTree2 implements BSTIF2 {
	public Node2 findNode(DateStack data) {
		return findNode(data, getRoot());
	}

	public Node2 findNode(DateStack data, Node2 node) {
		if (node == null) {
			return null;
		}
		if (data.compareTo(node.getData())>0) {
			node = findNode(data, node.getRight());
		} else if (((DateStack)data).compareTo(((DateStack)(node.getData())))<0) {
			node = findNode(data, node.getLeft());
		}
		return node;
	}

	public void insert(DateStack data) {
		if(findNode(data)==null) {
		setRoot(insertNode(data, getRoot())) ;}
	}

	public Node2 insertNode(DateStack data, Node2 node) {
		if (node == null) {
			node = new Node2(data);
		} else if (data.compareTo(node.getData())>=0) {
			node.setRight(insertNode(data, node.getRight()));
		} else {
			node.setLeft(insertNode(data, node.getLeft()));
		}
		return node;
	}

	public void delete(DateStack data) {
		setRoot(deleteNode(data, getRoot()));
	}

	public Node2 deleteNode(DateStack data, Node2 node) {
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

	public void deleteNodeWithTwoChildren(Node2 node) {
		Node2 inOrderSuccessor = findMin(node.getRight());
		node.setData(inOrderSuccessor.getData());
		node.setRight(deleteNode(inOrderSuccessor.getData(),inOrderSuccessor));
	}

	private Node2 findMin(Node2 node) {
		if (node == null) {
			return null;
		} else if (node.getLeft() == null) {
			return node;
		} else
			return findMin(node.getLeft());
	}

}
