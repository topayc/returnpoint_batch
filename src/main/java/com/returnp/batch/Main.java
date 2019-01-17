package com.returnp.batch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.returnp.batch.common.Plan;
import com.returnp.batch.scheduler.SchedulerCronTrigger;
import com.returnp.batch.task.DoJob1;

@Controller
@Component
public class Main implements ApplicationListener<ApplicationEvent>{
	
	private final Logger logger = LoggerFactory.getLogger(Main.class);
	private boolean initialized = false;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		if(!initialized) {
			logger.info("Event:"+event);
			setting();
		}
	}
	
	@RequestMapping("/")
	public void index() {
	}
	
	private void setting(){
		logger.info("RPAY 자동적립 배치 스케쥴러를 등록합니다");
		initialized = true;
		List<Plan> plan= new ArrayList<Plan>();
		//plan.add( new Plan( "0/10 * * * * ?" ,DoJob1.class));
		plan.add( new Plan("0 0 1 * * ?",DoJob1.class));
	    SchedulerCronTrigger.getInstance(plan);
	    logger.info("[ 0 0 1 * * ?] : RPAY 자동적립 배치 스케쥴러를 등록 완료 했습니다");
	}
}
