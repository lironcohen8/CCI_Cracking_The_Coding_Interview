import java.util.ArrayList;
import java.util.Stack;
public class RecursionAndDP {
	public int tripleStep(int n) {
		//O(2^n) time // O(n) recursion space
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		if (n == 3)
			return 4;
		else 
			return tripleStep(n-1) + tripleStep(n-2) + tripleStep(n-3); 
	} 
	public int tripleStepMemoHelp(int n, int[] memo) {
		// O(n) time, O(n) space
		if (n == 1)
			memo[1] = 1;
		else if (n == 2)
			memo[2] = 2;
		else if (n == 3)
			memo[3] = 4;
		else if (memo[n] == 0)
			memo[n] = tripleStepMemoHelp(n-1, memo) + tripleStepMemoHelp(n-2, memo) +  tripleStepMemoHelp(n-3, memo);
		return memo[n];
	}
	public int tripleStepMemo(int n) {
		return tripleStepMemoHelp(n, new int[n+1]);
	}
	public int tripleStepIter(int n) {
		// O(n) time, O(1) space
		int a = 1;
		int b = 2;
		int c = 4;
		int d = 0;
		for (int i = 4; i <= n; i++) {
			d = a + b + c;
			a = b;
			b = c;
			c = d;
		}
		return d;
	}
	public boolean robotInAGridHelp(int r, int c, int[][] limits) {
		//recursion, o(2^n) time, O(max(rows, columns)) space
		int rows = limits.length;
		int columns = limits[0].length;
		if ((r == rows) && (c == columns))
			return true;
		else if (limits[r][c] == 1) // there is an obstacle
			return false;
		else if (r == rows)
			return robotInAGridHelp(r, c+1, limits);
		else if (c == columns)
			return robotInAGridHelp(r+1, c, limits);
		else
			return robotInAGridHelp(r, c+1, limits) || robotInAGridHelp(r+1, c, limits);
	}
	public boolean robotInAGrid(int[][] limits) {
		return robotInAGridHelp(0, 0, limits);
	}
	public int robotInAGridMemoHelp(int r, int c, int[][] limits, int[][] memo) {
		int rows = limits.length;
		int columns = limits[0].length;
		if (r < 0 || c < 0)
			return -1;
		if ((r == rows) && (c == columns))
			memo[r][c] = 1;
		else if (limits[r][c] == 1) // there is an obstacle
			memo[r][c] = -1;
		else if (memo[r][c] == 0)
			memo[r][c] = (robotInAGridMemoHelp(r, c+1, limits, memo) == 1 || robotInAGridMemoHelp(r+1, c, limits, memo) == 1) ? 1 : -1;
		return memo[r][c];
			
	}
	public boolean robotInAGridMemo(int[][] limits) {
		int[][] memo = new int [limits.length][limits[0].length];
		return robotInAGridMemoHelp(0, 0, limits, memo) == 1;
	}
	public int magicIndexHelp(int[] arr, int start, int end) {
		if (end < start)
			return -1;
		int mid = (start + end) / 2;
		if (arr[mid] == mid)
			return mid;
		if (arr[mid] > mid)
			return magicIndexHelp(arr, start, mid - 1);
		else
			return magicIndexHelp(arr, mid + 1, end);
	}
	public int magicIndex(int[] arr) {
		// O(log n) time
		return magicIndexHelp(arr, 0, arr.length-1);
	}
	public int magicIndexDupHelp(int[] arr, int start, int end) {
		if (end < start)
			return -1;
		int mid = (start + end) / 2;
		if (arr[mid] == mid)
			return mid;
		if (arr[mid] > mid) {
			int firstTry = magicIndexDupHelp(arr, start, mid - 1); 
			return firstTry != -1 ? firstTry : magicIndexDupHelp(arr, arr[mid], end);
		}
		else {
			int firstTry = magicIndexDupHelp(arr, mid + 1, end); 
			return firstTry != -1 ? firstTry : magicIndexDupHelp(arr, start, arr[mid]);
		}
	}
	public int magicIndexDup(int[] arr) {
		// O(log n) time
		return magicIndexDupHelp(arr, 0, arr.length-1);
	}
	public ArrayList<ArrayList<Integer>> powerSetRec (ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> toAdd = new ArrayList<>();
		if (set.size() == 0) {
			return toAdd;
		}
		else {
			int num = set.get(set.size() - 1);
			set.remove(set.size()-1);
			ArrayList<ArrayList<Integer>> soFar = powerSetRec(set);
			toAdd.addAll(soFar);
			for (ArrayList<Integer> lst : toAdd) {
				lst.add(num);
			}
			toAdd.addAll(soFar);
		}
		return toAdd;
	}
	public ArrayList<ArrayList<Integer>> powerSet (ArrayList<Integer> set) {
		return powerSetRec(set);
	}
	public int recursiveMultiplyHelp(int a, int b) {
		// a is bigger so we'll do a + a + a b times
		// b operations
		if (b == 0)
			return 0;
		if (b == 1)
			return a;
		else
			return a + recursiveMultiplyHelp(a, b - 1);
	}
	public int recursiveMultiply(int a, int b) {
		return recursiveMultiplyBetter(Math.max(a, b), Math.min(a, b));
	}
	public int recursiveMultiplyBetter(int bigger, int smaller) {
		// O(log smaller) operations
		if (smaller == 0)
			return 0;
		if (smaller == 1)
			return bigger;
		int semiResult = recursiveMultiplyBetter(bigger, smaller >> 1); // divide by 2
		if (smaller % 2 == 0)
			return semiResult + semiResult;
		else
			return bigger + semiResult + semiResult;
	}
	public void towerOfHanoiHelp(int n, Stack<Integer> start, Stack<Integer> help, Stack<Integer> end) {
		towerOfHanoiHelp(n-1, start, end, help);
		System.out.println("moving " + n + "disc from " + start + " to " + end + ".");
		end.push(start.pop());
		towerOfHanoiHelp(n-1, help, start, end);
	}
	public void towerOfHanoi(int n) {
		Stack<Integer> a = new Stack<>();
		Stack<Integer> b = new Stack<>();
		Stack<Integer> c = new Stack<>();
		towerOfHanoiHelp(n , a, b, c);
	}
	public String insertCharAt(String str, Character ch, int index) {
		String result = "";
		return str.substring(0, index) + ch + str.substring(index);
	}
	public ArrayList<String> stringPermutations(String str) {
		ArrayList<String> result = new ArrayList<String>();
		if (str.length() == 0) {
			result.add("");
		}
		else {
			Character ch = str.charAt(0);
			ArrayList<String> resultSoFar = stringPermutations(str.substring(1));
			for (String st : resultSoFar) {
				for (int i = 0; i <= st.length(); i++) {
					result.add(insertCharAt(st, ch, i));
				}
			}
		}
		return result;
	}
}
