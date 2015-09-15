package br.com.implementations.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunnableTask {
	public static void main(String[] args) {
	   
	    Runnable runnable = new Runnable() {
	      public void run() {
	        // task to run goes here
	        System.out.println("Hello !!");
	      }
	    };
	    
	    long initialDelay = 0; 
	    
	    long period = 1; 
	    
		ScheduledExecutorService service = Executors
	                    .newSingleThreadScheduledExecutor();
	    service.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);
	  }
	}