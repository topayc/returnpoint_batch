package com.returnp.batch.service.interfaces;

import org.quartz.SchedulerException;

public interface BatchControlService {
	public void initBatch();
	public void startBatchAll() throws SchedulerException;
	public void stopBatch();
	public void testBatch();
	public void startBatchTarget();
	public void updateBatch();
}
