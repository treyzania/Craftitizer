package com.treyzania.craftitizer.job;

public class JobFailure {

	public final Job job;
	public final Exception reason;
	
	public JobFailure(Job job, Exception reason) {
		
		this.job = job;
		this.reason = reason;
		
	}
	
}
