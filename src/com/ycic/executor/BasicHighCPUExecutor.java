package com.ycic.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicHighCPUExecutor {

	public static void main(String[] args) {
		// much higher count for IO tasks
		int nThreads = 20;
		ExecutorService service = Executors.newFixedThreadPool(nThreads);	// create thread pool
		
		// submit the tasks for execution
		for (int i=0; i<nThreads; i++) {
			service.execute(new IOTask());
		}
		System.out.println("Thread " + Thread.currentThread().getName());
	}
}

class IOTask implements Runnable {

	@Override
	public void run() {
		// some CPU intensive operations, such as rendering, AI 
		System.out.println("Thread " + Thread.currentThread().getName());
	}
	
}
