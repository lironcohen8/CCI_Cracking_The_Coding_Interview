import java.util.LinkedList;
import java.util.ArrayList;

public class TreesAndGraphs {
	public class Node {
		private int val;
		private String state;
		private Node left;
		private Node right;
		private Node parent;
		
		public Node(int val) {
			this.val = val;
		}
	}
	public boolean routeBetweenNodes(Graph g, Node start, Node end) {
		if (start == end)
			return true;
		LinkedList<Node> q = new LinkedList<>();
		for (Node u : g.getNodes()) {
			u.state = "unvisited";
		}
		start.state = "visiting";
		q.add(start);
		Node u;
		while (!q.isEmpty()) {
			u = q.removeFirst();
			if (u != null) {
				for (Node v : u.getAdjacent()) {
					if (v.state.equals("unvisited")) {
						if (v == end)
							return true;
						else {
							v.state = "visiting";
							q.add(v);
						}
						
					}
				}
				u.state = "visited";
			}
		}
		return false;
	}
	public Node minimalTreeHelp(int[] arr, int start, int end) {
		if (end < start)
			return null;
		else {
			int mid = (start+end)/2;
			Node root = new Node(arr[mid]);
			root.left = minimalTreeHelp(arr, start, mid-1);
			root.right = minimalTreeHelp(arr, mid+1, end);
			return root;
		}
	}
	public Node minimalTree(int[] arr) {
		// O(n) time, O(n) space
		return minimalTreeHelp(arr, 0, arr.length-1);
	}
	public void listOfDepthsTraHelp(Node root, ArrayList<LinkedList<Node>> lists, int level) {
		//using regular traverse
		if (root != null) {
			if (lists.size() - 1 < level)
				lists.add(new LinkedList<>());
			lists.get(level).add(root);
			listOfDepthsTraHelp(root.left, lists, level + 1);
			listOfDepthsTraHelp(root.right, lists, level + 1);
		}
	}
	public ArrayList<LinkedList<Node>> listOfDepthsTra(Node root) {
		//O(n) time, O(n) space, some more additional space for recursion
		ArrayList<LinkedList<Node>> lists = new ArrayList<>();
		listOfDepthsTraHelp(root, lists, 0);
		return lists;
	}
	public ArrayList<LinkedList<Node>> listOfDepthsBFS(Node root) {
		//O(n) time, O(n) space
		ArrayList<LinkedList<Node>> lists = new ArrayList<>();
		int level = 0;
		lists.add(new LinkedList<Node>());
		if (root != null)
			lists.get(0).add(root);
		level++;
		while (lists.get(level - 1).size() > 0) {
			lists.add(new LinkedList<Node>());
			for (Node prev : lists.get(level - 1)) {
				if (prev.right != null)
					lists.get(level).add(prev.right);
				if (prev.left != null)
					lists.get(level).add(prev.left);
			}
			level++;
		}
		lists.remove(lists.size() - 1); // last empty list
		return lists;
	}
	public int height (Node node) {
		if (node == null)
			return -1;
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		if (leftHeight == Integer.MIN_VALUE || leftHeight == Integer.MIN_VALUE || Math.abs(rightHeight - leftHeight) > 1) // not balanced
			return Integer.MIN_VALUE;
		else
			return Math.max(height(node.right), height(node.left)) + 1;
	}
	public boolean isBalanced(Node root) {
		return height(root) != Integer.MIN_VALUE;
	}
	public boolean validateBSTHelp(Node root, int min, int max) { 
		if (root == null)
			return true;
		if (root.val > max || root.val < min)
			return false;
		else
			return validateBSTHelp(root.right, root.val, max) && validateBSTHelp(root.left, min, root.val); 
	}
	public boolean validateBSTMinMax(Node root) {
		// O(n) time, O(logn) due to recursion
		return validateBSTHelp(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	public ArrayList<Integer> treeToArray(Node root, ArrayList<Integer> arrList) {
		if (root == null)
			return null;
		else {
			treeToArray(root.left, arrList);
			arrList.add(root.val);
			treeToArray(root.right, arrList);
		}
		return arrList;
	}
	public boolean checkIfSorted(ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size() - 1; i++) {
			if (arr.get(i) > arr.get(i+1))
				return false;
		}
		return true;
	}
	public boolean validateBSTInOrder(Node root) {
		ArrayList<Integer> arr = treeToArray(root, new ArrayList<Integer>());
		return checkIfSorted(arr);
	}
	public Node successor(Node node) {
		
	}
}
