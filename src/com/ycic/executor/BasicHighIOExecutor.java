package com.ycic.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicHighIOExecutor {

	public static void main(String[] args) {
		// get count of available cores
		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);	// create thread pool
		
		// submit the tasks for execution
		for (int i=0; i<20; i++) {
			service.execute(new CPUIntensiveTask());
		}
		System.out.println("Thread " + Thread.currentThread().getName());
	}
}

class CPUIntensiveTask implements Runnable {

	@Override
	public void run() {
		// some CPU intensive operations, such as rendering, AI 
		System.out.println("Thread " + Thread.currentThread().getName());
	}
	
}
