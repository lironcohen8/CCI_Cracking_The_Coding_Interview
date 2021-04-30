import java.util.LinkedList;
import java.util.ArrayList;

public class TreesAndGraphs {
	public class Node {
		private int val;
		private String state;
		private Node left;
		private Node right;
		
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
