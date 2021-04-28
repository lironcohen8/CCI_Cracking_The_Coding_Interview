import java.util.Arrays;
import java.util.HashMap;

public class ArraysAndStrings {

	public static boolean isUnique(String str) {
		// one solution using hashmap, O(n)
		HashMap<Character, Integer> hm = new HashMap<>();
		for (int i = 0; i<str.length(); i++) {
			if (hm.containsKey(str.charAt(i)))
					return false;
			else {
				hm.put(str.charAt(i), 1);
			}
		}
		//return true;
		
		// without hashmap, O(n^2) 
		for (int i = 0; i < str.length(); i++) {
			for (int j = i+1; j < str.length(); j++) {
				if (str.charAt(j) == str.charAt(i))
					return false;
			}
		}
		//return true;
		
		//with sorting and arrays, O(n)
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		for (int i = 0 ; i < arr.length - 1; i++) {
			if (arr[i] == arr[i+1])
				return false;
		}
		return true;
	}
	public static boolean checkPermutation(String str1, String str2) {
		// identical sorted structure / same count for chars in strings
		
		//using arrays, O(n) time, O(n) space
		if (str1.length() != str2.length())
			return false;
		
		char[] arr1 = str1.toCharArray();
		Arrays.sort(arr1);
		char[] arr2 = str2.toCharArray();
		Arrays.sort(arr2);
		
		for (int i = 0 ; i < arr1.length; i++) {
			if (arr1[i] != arr2[i])
				return false;
		}
		//return true;
		
		//using hashmap and counting, O(n) time O(n)
		HashMap<Character, Integer> hm = new HashMap<>();
		for (int i = 0; i < str1.length(); i++)
		{
			if (!hm.containsKey(str1.charAt(i)))
				hm.put(str1.charAt(i), 0);
			hm.put(str1.charAt(i), hm.get(str1.charAt(i)) + 1);
		}
		for (int i = 0; i < str1.length(); i++)
		{
			if (!hm.containsKey(str2.charAt(i)))
				return false;
			else if (hm.get(str2.charAt(i)) <= 0)
				return false;
			else
				hm.put(str2.charAt(i), hm.get(str2.charAt(i)) - 1);
		}
		return true;
	}
	public static String URLify(char[] arr, int length) { ////
		int spaceCount = 0;
		for (int i = 0 ; i < length ; i++) {
			if (arr[i] == ' ') 
				spaceCount++;
		}
		int index = length + (spaceCount * 2);
		if (length < arr.length)
			arr[length] = '\0';
		for (int j = length - 1 ; j >= 0 ; j--) {
			if (arr[j] == ' ') {
				arr[index-1] = '0';
				arr[index-2] = '2';
				arr[index-3] = '%';
				index -= 3;
			}
			else {
				arr[index - 1] = arr[j];
				index--;
			}
		}
		return String.valueOf(arr);
	}
	public static boolean palindromePermutation(String str) {
		// every letter has an even number of occurrances but (possibly) one
		// O(n) time, O(n) space
		HashMap<Character, Integer> hm = new HashMap<>();
		str = str.toLowerCase();
		for (int i = 0 ; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				if (!hm.containsKey(str.charAt(i))) {
					hm.put(str.charAt(i), 0);
				}
				hm.put(str.charAt(i), hm.get(str.charAt(i)) + 1);
			}
		}
		int count = 0;
		for (char key : hm.keySet()) {
			if (hm.get(key) % 2 != 0)
				count++;
		}
		return count<=1;
	}
	public static boolean oneAway(String str1, String str2) {
		// O(n) time, need to remember the two pointers
		if (Math.abs(str1.length()-str2.length()) > 1)
				return false;
		int count = 0;
		int index1 = 0;
		int index2 = 0;
		if (str1.length() == str2.length()) { // replace
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) != str2.charAt(i))
					count++;
			}
			return count <= 1;
		}
		else if (str1.length() - str2.length() == 1) { //remove
			while (index1 < str1.length() && index2  < str2.length()) {
				if (str1.charAt(index1) != str2.charAt(index2)) {
					if (index1 != index2)
						return false;
					index1++;
				}
				else {
					index1++;
					index2++;
				}
			}
			return true;
		}
		else { //insert
			while (index1 < str1.length() && index2  < str2.length()) {
				if (str1.charAt(index1) != str2.charAt(index2)) {
					if (index1 != index2)
						return false;
					index2++;
				}
				else {
					index1++;
					index2++;
				}
			}
		}
		return true;
	}
	public static String stringCompression (String str) {
		// O(n) time, O(n) space
		StringBuilder result = new StringBuilder();
		int i = 0;
		while (i < str.length()) {
			char letter = str.charAt(i);
			int count = 0;
			while(i < str.length() && str.charAt(i)  == letter) {
				count++;
				i++;
			}
			result.append(letter);
			result.append(count);
		}
		if (result.toString().length() >= str.length())
			return str;
		return result.toString();
			
	}
	public static int[][] rotateMatrix(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0 ; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][n-i-1];
				matrix[j][n-i-1] = matrix[n-i-1][n-j-1];
				matrix[n-i-1][n-j-1] = matrix[n-j-1][i];
				matrix[n-j-1][i] = tmp;
			}
		}
		return matrix;
	}
	public static int[][] zeroMatrix(int[][] matrix) {
		// O(n^2) time, O(n) space
		boolean[] rows = new boolean[matrix.length];
		boolean[] columns = new boolean[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					rows[i] = true;
					columns[j] = true;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (rows[i] || columns[j]) {
					matrix[i][j] = 0;
				}
			}
		}
		return matrix;
	}
	public static boolean isSubstring(String s1, String s2) {
		//O(1)
		return s1.contains(s2);
	}
	public static boolean stringRotation(String s1, String s2) {
		//O(n^2) time, O(n) space
		if (s1.length() != s2.length())
			return false;
		/*StringBuilder sb1 =  new StringBuilder(s1);
		for (int i = 0 ; i < s1.length() - 1; i++) {
			String part1 = sb1.substring(0, i);
			String part2 = sb1.substring(i);
			if (isSubstring(s2, part1) && isSubstring(s2, part2))
				return true;
		}
		return false;*/
		
		//O(n) time, O(n) space
		String s1s1 = s1 + s1;
		return isSubstring(s1s1, s2);
	}
	public static void main(String[] args) {
		//System.out.println(isUnique("sdgdsgg"));
		//System.out.println(checkPermutation("abcd","abce"));
		//String str = "Mr John Smith     ";
		//String result = URLify(str.toCharArray(),13);
		//System.out.println(result);
		//System.out.println(palindromePermutation("Tact Coa onk"));
		//System.out.println(oneAway("paledd", "pale"));
		//System.out.println(stringCompression("aabcaa"));
		//int[][] mat = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		//rotateMatrix(mat);
		//System.out.println(stringRotation("abcds", "dsabb"));
	}

}
