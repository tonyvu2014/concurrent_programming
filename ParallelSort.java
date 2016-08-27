import java.lang.IllegalArgumentException;
import java.util.*;


public class ParallelSort {
	public static void main(String[] args) {
		
		if (args.length < 1) {
			throw new IllegalArgumentException("Must enter a number");
		}
		
		int n= 0;
		try {
			n = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number");
		}
		
		Random random = new Random();
		int[] array = new int[n];
		for (int i=0;i<n;i++) {
			array[i] = random.nextInt(1000);
		}
		
		int[] array1 = Arrays.copyOf(array, array.length);
		long startTime = System.currentTimeMillis();
		Arrays.sort(array1);
		long endTime = System.currentTimeMillis();
		System.out.println("Running Arrays.sort() in " + (endTime-startTime) + " ms");
		
		int[] array2 = Arrays.copyOf(array, array.length);
		startTime = System.currentTimeMillis();
		Arrays.parallelSort(array2);
		endTime = System.currentTimeMillis();
		System.out.println("Running Arrays.parallelSort() in " + (endTime-startTime) + " ms");
		
	}
		
}