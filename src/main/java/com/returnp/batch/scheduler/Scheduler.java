package com.returnp.batch.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.returnp.batch.common.AppConstants;
import com.returnp.batch.common.Plan;
import com.returnp.batch.service.interfaces.PointConversionTransactionService;
import com.returnp.batch.task.DoJob1;
import com.returnp.batch.task.DoJob2;
import com.returnp.batch.task.DoJob3;

@Component
public class Scheduler {	
	/*
	@Autowired private PointConversionTransactionService pointConversionTransactionService;
	
	@Scheduled(fixedRate  = AppConstants.DELAY)
	public void doExec() {
		try {
			//exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*public void exec() {		
		
		List<Plan> plan= new ArrayList<Plan>();
		plan.add( new Plan("0/60 * * * * ?",DoJob1.class));
		plan.add( new Plan("0/3200 * * * * ?",DoJob2.class));
		plan.add( new Plan("0/500 * * * * ?",DoJob3.class));
		
	    SchedulerCronTrigger.getInstance(plan,pointConversionTransactionService);
	}*/
	

}
