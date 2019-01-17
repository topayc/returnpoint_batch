package com.returnp.batch.service;

import java.util.Date;
import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import com.returnp.batch.scheduler.SchedulerCronTrigger;
import com.returnp.batch.service.interfaces.BatchControlService;

@Service
public class BatchContolServiceImpl implements BatchControlService {

	@Override
	public void initBatch() {
	}

	@Override
	public void startBatchAll() throws SchedulerException {
		Scheduler scheduler = SchedulerCronTrigger.getInstance().getScheduler();
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				String jobGroup = jobKey.getGroup();
				//List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				scheduler.triggerJob(jobKey);
			}
		}
	}

	@Override
	public void stopBatch() {

	}

	@Override
	public void testBatch() {
		
	}

	@Override
	public void startBatchTarget() {
		
	}

	@Override
	public void updateBatch() {
		// TODO Auto-generated method stub
		
	}


}
