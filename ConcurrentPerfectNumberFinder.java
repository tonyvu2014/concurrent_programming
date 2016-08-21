import java.lang.IllegalArgumentException;
import java.util.concurrent.*;


public class ConcurrentPerfectNumberFinder {
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
		
		long startTime = System.currentTimeMillis();
		findPerfectNumber(n);
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println("Running findPerfectNumber() in " + runningTime + " ms");
		
		
		startTime = System.currentTimeMillis();
		findPerfectNumberInParallel(n);
		endTime = System.currentTimeMillis();
		runningTime = endTime - startTime;
		System.out.println("Running findPerfectNumberInParallel() in " + runningTime + " ms");
	}
	
	
	private static void findPerfectNumber(int n) {
		System.out.println("Perfect numbers from 1 to " + n + ":");
		for (int i=2; i<=n;i++) {
			if (isPerfectNumber(i)) {
				System.out.println(i);
			}
		}
	}
	
	private static void findPerfectNumberInParallel(int n) {
		System.out.println("Perfect numbers from 1 to " + n + ":");
        ExecutorService executor =  Executors.newFixedThreadPool(3);
		
		for (int i=2; i<=n;i++) {
			Callable<Boolean> perfectNumberCheckerRunner = new PerfectNumberChecker(i);
			Future<Boolean> checkResultFuture = executor.submit(perfectNumberCheckerRunner);
			try {
				if (checkResultFuture.get()) {
					System.out.println(i);
				}
			} catch (ExecutionException e) {
			    System.out.println("ExecutionException()");
			} catch (InterruptedException e) {
			    System.out.println("InterruptedException()");				
			} catch (CancellationException e) {
			    System.out.println("CancellationException()");				
			}
		}		
		
		try {
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("Tasks interrupted");
		}
		finally {
		    if (!executor.isTerminated()) {
		        System.err.println("Cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		}
		
	}
	
	
	private static boolean isPerfectNumber(int number) {
		int sum = 0;
		for (int i = 1; i < number; i++) {
			if (number % i == 0) {
				sum += i;
			}
			if (sum > number) {
				return false;
			}
		}	
		
		if (sum == number) {
			return true;
		} 
		
		return false;
		
	}
	
}

class PerfectNumberChecker implements Callable<Boolean> {
	private final int number;
	
	public PerfectNumberChecker(int number) {
		this.number = number;
	}
	
	@Override
	public Boolean call() throws Exception {
		int sum = 0;
		for (int i = 1; i < number; i++) {
			if (number % i == 0) {
				sum += i;
			}
			if (sum > number) {
				return false;
			}
		}	
	
		if (sum == number) {
			return true;
		} 
	
		return false;
	}
}