package com.treyzania.craftitizer.job;

import java.util.ArrayList;
import java.util.List;

public class JobList {

	private final List<Job> jobs;
	private boolean completed;
	
	public JobList() {
		
		this.jobs = new ArrayList<>();
		
	}
	
	private void checkMutable() {
		if (completed) throw new IllegalStateException("This job list has already been ran!");
	}
	
	public List<JobFailure> execute() {
		
		this.checkMutable();
		
		List<JobFailure> failures = new ArrayList<>();
		
		// Iterate through the jobs, trying to run them, and keeping track of who failed.
		for (Job job : this.jobs) {
			
			try {
				job.run();
			} catch (Exception e) {
				failures.add(new JobFailure(job, e));
			}
			
		}
		
		this.completed = true;
		return failures;
		
	}
	
	public void add(Job job) {
		
		this.checkMutable();
		this.jobs.add(job);
		
	}
	
	/**
	 * @return True if jobs can still be added to it 
	 */
	public boolean isLive() {
		return !this.completed;
	}
	
}
