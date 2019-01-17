package com.returnp.batch.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.returnp.batch.service.interfaces.PointConversionTransactionService;
@Component
public class DoJob3 implements Job{

	//@Autowired private PointConversionTransactionService pointConversionTransactionService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		try
		{
			System.out.println("실행:"+"DoJob3");
			
		}catch(Exception e){		
			System.out.println("3초마다실행:error");
			e.printStackTrace();
		}
		
	}

}
	
