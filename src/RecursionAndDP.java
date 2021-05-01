
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
