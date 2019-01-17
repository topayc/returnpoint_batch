package com.returnp.batch.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.returnp.batch.common.Plan;

public class SchedulerCronTrigger{

	private final Logger logger = LoggerFactory.getLogger(SchedulerCronTrigger.class);
	private static volatile SchedulerCronTrigger INSTANCE;
	private static StdSchedulerFactory schedFact = null;
	private Scheduler scheduler = null;
	private SchedulerCronTrigger() {}
	private List<Plan> plans;
	
	public static SchedulerCronTrigger getInstance() {
		return INSTANCE;
	}
	
	public List<Plan> getPlanList(){
		return this.plans;
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	public static SchedulerCronTrigger getInstance(List<Plan> plan)
	{
		if( INSTANCE == null )		
		{	
			synchronized (SchedulerCronTrigger.class){		
				if( INSTANCE == null)	{
					INSTANCE = new SchedulerCronTrigger();	
					INSTANCE.initInstance(plan);	
				}				
			}
		}
		return INSTANCE;
	}
	private void initInstance(final List<Plan> plan)
	{
		logger.info("**************************************");   
		logger.info("Start Push Schedule~!");	
		logger.info("**************************************");   
		this.plans = plan;
		
		try{
			if(schedFact==null) {
				
			   logger.info("Create scheduler");
				
			   // 스케쥴 생성후 시작			
			   schedFact = new StdSchedulerFactory();
			   scheduler = (Scheduler) schedFact.getScheduler();

			   if(!scheduler.isStarted())
			   {
				   	logger.info("isStarted:"+false);
				    scheduler.start();
				    
				    for(Plan p : plan) {
				    	
				    	logger.info("Start scheduler with ["+p.getCronStr()+"]");
						
					    JobDataMap jobData = new JobDataMap();
				        jobData.put("task", p.getJobClass().getName());				        
				        logger.info("Job Class Name " + p.getJobClass().getName());				        
				        JobDetail doJob = JobBuilder.newJob(p.getJobClass()).usingJobData(jobData).build();
				      		
					    CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
				    			.withIdentity("trigger_"+jobData.getString("task"), p.getJobClass().getName() )
				    		    .withSchedule(craeteSchedule(p.getCronStr())).build(); 
					    
					    scheduler.scheduleJob(doJob, trigger);
					    
					    
				    }
			   }
			   logger.info("isStarted:"+true);
			}
			logger.info("**************************************");   
		
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static ScheduleBuilder<?> craeteSchedule(String cronExpression){
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
		return builder;
	}
	
	public  void stopAll() throws SchedulerException {
		schedFact = new StdSchedulerFactory();
		scheduler = (Scheduler) schedFact.getScheduler();
		scheduler.shutdown();
	}
	
}

