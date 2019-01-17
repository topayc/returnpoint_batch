package com.returnp.batch.task;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.returnp.batch.common.CommonUtil;
import com.returnp.batch.model.PointConversionTransaction;
import com.returnp.batch.scheduler.SchedulerCronTrigger;
import com.returnp.batch.service.interfaces.PointConversionTransactionService;
@Component
public class DoJob1 implements Job{

	private final Logger logger = LoggerFactory.getLogger(DoJob1.class);
	private PointConversionTransactionService service;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		try
		{
			JobDataMap jobDataMap = context.getMergedJobDataMap(); 	
			service = (PointConversionTransactionService)CommonUtil.ctx.getBean(PointConversionTransactionService.class);
			int result = service.updateByStatus();
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("RPAY 자동적립 배치 프로세스 실행 : ");
			sBuilder.append("[" + CommonUtil.getTestToday() + "] "+result+" 건의 포인트가 성공적으로 전화되었습니다.");
			logger.info(sBuilder.toString());
			//CommonUtil.sendMessage(message);
			
		}catch(Exception e){	
			String message;
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				String sStackTrace = sw.toString();
				message = "[" + CommonUtil.getTestToday() + "] 시스템 장애로 인해 포인트 전환이 실패하였습니다.\n"
						+sStackTrace;
				
				logger.error(message);
				//CommonUtil.sendMessage(message);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		
	}
	
}
	
