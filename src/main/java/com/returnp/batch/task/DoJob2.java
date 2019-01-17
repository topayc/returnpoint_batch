package com.returnp.batch.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
@Component
public class DoJob2  implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		try
		{
			System.out.println("실행:"+"DoJob2");
			
		}catch(Exception e){		
			
			e.printStackTrace();
		}
		
	}

}
	
