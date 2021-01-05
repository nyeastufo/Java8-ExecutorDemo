package com.ycic.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicExecutorDemo {

	public static void main(String[] args) {
		
		int nThreads = 10;
		ExecutorService service = Executors.newFixedThreadPool(nThreads);	// create thread pool
		
		// submit the tasks for execution
		for (int i=0; i<nThreads; i++) {
			service.execute(new Task());
		}
		System.out.println("Thread " + Thread.currentThread().getName());
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName());
	}
	
}
