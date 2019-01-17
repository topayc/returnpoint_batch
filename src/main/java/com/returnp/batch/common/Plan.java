package com.returnp.batch.common;

import org.quartz.Job;

public class Plan {
	
	public Plan() {
		
	}
	
	public Plan(String cronstr,Class<? extends Job> jobcls) {
		this.cronStr = cronstr;
		this.jobClass = jobcls;
	}
	
	private String cronStr;
	private Class<? extends Job> jobClass;	
	
	public String getCronStr() {
		return cronStr;
	}
	public void setCronStr(String cronStr) {
		this.cronStr = cronStr;
	}
	public Class<? extends Job> getJobClass() {
		return jobClass;
	}
	public void setJobClass(Class<? extends Job> jobClass) {
		this.jobClass = jobClass;
	}
}
