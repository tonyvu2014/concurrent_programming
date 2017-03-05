import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		
		ScheduledFuture scheduleFuture= 
		    scheduledExecutorService.schedule(new Runnable() {
		        public void run() {
			        LocalDateTime now = LocalDateTime.now();
					System.out.println("Called by schedule at: " + dtf.format(now));
		        }
		    },
		    5,
		    TimeUnit.SECONDS);
			
			
	   ScheduledFuture scheduleAtFixedRateFuture =
			scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
	            public void run() {
			        LocalDateTime now = LocalDateTime.now();
					System.out.println("Called by scheduleAtFixedRate at: " + dtf.format(now));
					try {
     					Thread.sleep(2000);
					} catch  (InterruptedException ex) {
						ex.printStackTrace();
					}
		        }
			},
			0,
		    5,
		    TimeUnit.SECONDS);
			
	   ScheduledFuture scheduleWithFixedDelayFuture =
	 		scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
	 	        public void run() {
			        LocalDateTime now = LocalDateTime.now();
					System.out.println("Called by scheduleWithFixedDelay at: " + dtf.format(now));
					try {
     					Thread.sleep(2000);
					} catch  (InterruptedException ex) {
						ex.printStackTrace();
					}
	            }
	 		},
			0,
	 		5,
	 		TimeUnit.SECONDS);
	    	
	}
	
}