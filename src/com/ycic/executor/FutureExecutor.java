package com.ycic.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExecutor {

	public static void main(String[] args) {
		// create multiple thread pool
		int nThreads = 20;
		ExecutorService service = Executors.newFixedThreadPool(nThreads);	// create thread pool
		
		// submit the tasks for execution
		List<Future> allFutures = new ArrayList<>();
		
		for (int i=0; i<nThreads; i++) {
			//service.execute(new FutureTask());
			//service.execute(new CallableTask());	// callable can not be executed here
			Future<Integer> future = service.submit(new CallableTask());		// callable can be submitted
			// future is a placeholder for a future value	
			allFutures.add(future);
		}
		
		// 20 futures, with 20 placeholders
		
		// perform some unrelated operations here, then check the future
		// task 100 seconds, some future complete, some not
		
		for (int i = 0; i < nThreads; i++) {
			Future<Integer> future = allFutures.get(i);
			try {
				Integer result = future.get();		// blocking until get a future value
				System.out.println("Get the future " + result); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread " + Thread.currentThread().getName());
		
		/*
		// cancel the task
		future.cancel(false);
		
		// return true if task was cancelled
		future.isCannelled;
		
		// return true if task is completed
		future.isDone();
		*/
	}
}

class FutureTask implements Runnable {

	@Override
	public void run() {
		// some CPU intensive operations, such as rendering, AI 
		System.out.println("Thread " + Thread.currentThread().getName());
		// return 3;		// can not return value from thread runnable
	}
}

class CallableTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int newValue = new Random().nextInt();
		System.out.println("Thread " + Thread.currentThread().getName() + " new random number " + newValue);
		return newValue;
	}
	
}
