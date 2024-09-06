
public class BinaryBaseTree2 implements BinaryTree2 {
	private Node2 root;

	public Node2 getRoot() {
		return root;
	}

	public void setRoot(Node2 root) {
		this.root = root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		appendStringToTree(builder, root);
		return builder.toString();
	}

	public String backward() {
		StringBuilder builder = new StringBuilder();
		backwardString(builder, root);
		return builder.toString();
	}

	void backwardString(StringBuilder builder, Node2 node) {

		if (node.getRight() != null) {
			backwardString(builder, node.getRight());
		}
		appendInfo(builder, node);
		if (node.getLeft() != null) {
			backwardString(builder, node.getLeft());
		}
	}

	void appendStringToTree(StringBuilder builder, Node2 node) {
		if (node.getLeft() != null) {
			appendStringToTree(builder, node.getLeft());
		}
		appendNode(builder, node);
		if (node.getRight() != null) {
			appendStringToTree(builder, node.getRight());
		}
	}

	void appendNode(StringBuilder builder, Node2 node) {
		builder.append(node.getData().toString() + " \n");
	}
	void appendInfo(StringBuilder builder, Node2 node) {
		builder.append(((DateStack)(node.getData())).getStack().toString() + "");
	}

}
