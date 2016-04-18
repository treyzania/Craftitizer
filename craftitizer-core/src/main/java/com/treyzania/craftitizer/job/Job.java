package com.treyzania.craftitizer.job;

public abstract class Job {
	
	/**
	 * If the job does not complete exactly as expected, should throw an exception.
	 */
	public void run() {
		
	}
	
	public abstract BuildStage getStage();
	
}
