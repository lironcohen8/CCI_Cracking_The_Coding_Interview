import java.util.LinkedList;
import java.util.Stack;

public class StacksAndQueues {
	public class ThreeStacksInArray {
		// three stacks in one array
		private int numberOfStacks = 3;
		private int stackCapacity;
		private int[] values;
		private int[] sizes;
		
		public ThreeStacksInArray(int capacity) {
			this.stackCapacity = capacity;
			this.values = new int[capacity * numberOfStacks];
			this.sizes = new int[numberOfStacks];
			this.sizes = new int[numberOfStacks];
		}
		
		public void push(int stackNum, int data) {
			if (sizes[stackNum] >= this.stackCapacity)
				return;
			else {
				values[topIndex(stackNum)+1] = data; 
			}
			sizes[stackNum]++;
		}
		
		public int peek(int stackNum) {
			if (sizes[stackNum] == 0)
				return -1;
			else return values[topIndex(stackNum)];
		}
		
		public int pop(int stackNum) {
			if (sizes[stackNum] == 0)
				return -1;
			else { 
				int val = values[topIndex(stackNum)];
				values[topIndex(stackNum)] = 0;
				sizes[stackNum]--;
				return val;
			}
		}
		
		public boolean isEmpty(int stackNum) {
			return sizes[stackNum] == 0;
		}
		
		private int topIndex(int stackNum) {
			int offset = this.stackCapacity * stackNum;
			int size = sizes[stackNum];
			return offset + size - 1;
		}
		
	}
	public class MinStack {
			private StackNode top;
		public class StackNode {
			private int data;
			private int minSoFar;
			private StackNode next;
			
			public StackNode(int data) {
				this.data = data;
			}
		}
		public int top() {
			return this.top.data;
		}
		
		public int pop() {
			if (this.top == null)
				return -1;
			int val = this.top.data;
			this.top = this.top.next;
			return val;
		}
		
		public void push(int data) {
			StackNode node = new StackNode(data);
			if (this.top == null)
				node.minSoFar = data;
			else
				node.minSoFar = Math.min(top.minSoFar, data);
		}
		
		public int min() {
			return this.top.minSoFar;
		}
	}
	public class SetOfStacks<T> {
		private int capacity;
		private LinkedList<Stack<T>> list;
		
		public SetOfStacks(int capacity) {
			this.capacity = capacity;
			list = new LinkedList<>();
			list.add(new Stack<T>());
		}
		
		public void push(T element) {
			Stack<T> st = list.get(list.size() - 1); 
			if (st.size() == capacity - 1) { 
				st.push(element);
				list.add(new Stack<T>());
			}
			else {
				st.push(element);
			}
		}
		
		public T top() {
			Stack<T> st = list.get(list.size() - 1);
			if (st.isEmpty())
				return null;
			else 
				return st.peek();
		}
		
		public T pop() {
			Stack<T> st = list.get(list.size() - 1);
			T result = st.pop();
			if (st.isEmpty()) {
				list.removeLast();
			}
			return result;
		}
		
		public T popAt(int index) {
			T result = list.get(index).pop();
			if (list.get(index).isEmpty())
				list.remove(index);
			return result;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}
