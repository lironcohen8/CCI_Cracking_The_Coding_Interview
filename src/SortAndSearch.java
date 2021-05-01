import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;

public class SortAndSearch {
	public void sortedMerge(int[] A, int[] B) {
		int lastA = A.length -1;
		while (A[lastA] == 0)
			lastA--;
		int lastB = B.length -1;
		int index = A.length - 1;
		while (lastA >= 0 && lastB >= 0) {
			if (A[lastA] > B[lastB]) {
				A[index] = A[lastA];
				lastA--;
			}
			else {
				A[index] = B[lastB];
				lastB--;
			}
			index--;
		}
	}
	public class AnagramComparator implements Comparator<String> {
		public String sortChars(String str) {
			char[] arr = str.toCharArray();
			Arrays.sort(arr);
			return new String(arr);
		}
		
		public int compare(String str1, String str2) {
			return sortChars(str1).compareTo(sortChars(str2));
		}
	}
	public void sortAnagrams(String[] arr) {
		Arrays.sort(arr, new AnagramComparator());
	}
	public void sortAnagramsBetter(String[] arr) {
		HashMap<String, ArrayList<String>> hm = new HashMap<>();
		for (String str : arr) {
			char[] strArr = str.toCharArray();
			Arrays.sort(strArr);
			String sorted = new String(strArr);
			if (!hm.containsKey(hm))
				hm.put(sorted, new ArrayList<String>());
			hm.get(sorted).add(str);
		}
		int index = 0;
		for (String str : hm.keySet()) {
			for (String word : hm.get(str)) {
				arr[index] = word;
				index++;
			}
		}
	}
	public int searchInRotatedArrHelp(int[] arr, int n, int start, int end) {
		if (end < start)
			return -1;
		int mid = (start + end) / 2;
		if (arr[mid] == n)
			return mid;
		else if (arr[mid] < arr[end]) { // right is normally ordered
			if (arr[mid] < n) // n is in right side
				return searchInRotatedArrHelp(arr, n, mid + 1, end);
			else // n is in the left side
				return searchInRotatedArrHelp(arr, n, start , mid - 1);
		}
		else if (arr[start] < arr[mid]){ // left is normally orderd
			if (arr[mid] > n) // n is in the left side
				return searchInRotatedArrHelp(arr, n, start , mid - 1);
			else // n is in the right side
				return searchInRotatedArrHelp(arr, n, mid + 1, end);
		}
		else if (arr[mid] == arr[end] && arr[mid] != arr[start]) {
			return searchInRotatedArrHelp(arr, n, start , mid - 1);
		}
		else if (arr[start] == arr[mid] && arr[mid] != arr[end]) {
			return searchInRotatedArrHelp(arr, n, mid + 1, end);
		}
		else {
			int result = searchInRotatedArrHelp(arr, n, start , mid - 1); 
			return result == -1 ? searchInRotatedArrHelp(arr, n, mid + 1, end) : result;
		}
	}
	public int searchInRotatedArr(int[] arr, int n) {
		return searchInRotatedArrHelp(arr, n, 0, arr.length-1);
	}
}
