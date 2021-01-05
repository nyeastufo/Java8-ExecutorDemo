package com.ycic.executor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor {

	public static void main(String[] args) {
		// much higher count for IO tasks
		int nThreads = 20;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(nThreads);	// create thread pool
		
		// task to run after 5 seconds delay
		service.schedule(new SchTask(), 5, TimeUnit.SECONDS);
		
		// task to run repeatedly every 5 seconds
		service.scheduleAtFixedRate(new SchTask(), 10, 5, TimeUnit.SECONDS);
		
		// task to run repeatedly 5 seconds after previous task completes
		service.scheduleWithFixedDelay(new SchTask(), 10, 5, TimeUnit.SECONDS);
		
		// initiate shutdown
		service.shutdown();
		
		// will throw RejectionExecutionException
		//service.execute(new SchTask());
		
		// will return true, since shutdown has begun
		service.isShutdown();
		
		// will return true if all tasks are completed, including queued ones
		service.isTerminated();
		
		// block until all tasks are completed or if timeout occurs
		try {
			service.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// will initiate shutdown and return all queued tasks
		List<Runnable> runnables = service.shutdownNow();
	}
}

class SchTask implements Runnable {

	@Override
	public void run() {
		// some CPU intensive operations, such as rendering, AI 
		System.out.println("Thread " + Thread.currentThread().getName());
	}
	
}
