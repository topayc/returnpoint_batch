package com.returnp.batch.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.batch.dto.BaseResponse;
import com.returnp.batch.service.interfaces.BatchControlService;

@Controller
public class BatchController extends ApplicationController{
	public class BatchCommand {
		public static final String BATH_CMD_INIT= "INIT_BATCH"; 											/* 배치 초기화 */
		public static final String BATH_CMD_START_ALL = "START_BATCH_ALL"; 						/* 전체 해당 회원 적립 전환 배치 시작 */
		public static final String BATH_CMD_START_MEMBER  = "START_BATCH_MEMBER"; 		/* 특정 타켓(회원)  배치 즉시 시작 */
		public static final String BATH_CMD_STOP  = "STOP_BATCH"; 										/* 적립 전환 배치 중지*/
		public static final String BATH_CMD_UPDATE = "UPDATE_BATCH"; 								/* 적립 전환 배치 수정*/
		public static final String BATH_CMD_TEST = "TEST_BATCH"; 										/* 적립 전환 테스트*/
	}

	@Autowired 
	BatchControlService batchService;
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/batchControl", method = RequestMethod.GET)
	public BaseResponse  batchControl(
			@RequestParam(value = "batchCmd", required = true) String  batchCmd	,		 						 /* 명령어 */
			@RequestParam(value = "batchData", required = false) String  batchData, 								 /* 명령어에 따른 데이타*/
			@RequestParam(value = "batchCount", required = false, defaultValue = "1") int batchCount, 		 /* 명령어에 따른 데이타*/
			@RequestParam(value = "batchPass", required = true, defaultValue = "") String  batchPass 		 	 /* 배치 실행 비밀번호*/
			
			) {
		
		BaseResponse res = new BaseResponse();
		if (StringUtils.isEmpty(batchPass) || !StringUtils.equals(batchPass, "a9831000eveangun")) {
			this.setErrorRespone(res, batchCmd + " : 명령 실행 권한이 없습니다" );
			return res;
		}
		
		try {
			switch(batchCmd) {
			case  BatchCommand.BATH_CMD_INIT:
				this.batchService.initBatch();
				break;
			case  BatchCommand.BATH_CMD_START_ALL:
				this.batchService.startBatchAll();
				break;
			case  BatchCommand.BATH_CMD_START_MEMBER:
				this.batchService.startBatchTarget();
				break;
			case  BatchCommand.BATH_CMD_STOP:
				this.batchService.stopBatch();
				break;
			case  BatchCommand.BATH_CMD_UPDATE:
				this.batchService.updateBatch();
				break;
			case  BatchCommand.BATH_CMD_TEST:
				this.batchService.testBatch();
				break;
			}
			this.setSuccessRespone(res, batchCmd + " : 배치 컨트롤러 성공");
		}catch(Exception e) {
			this.setErrorRespone(res, batchCmd + " : " + e.getMessage() );
		}finally {
			return res;
		}
	}
}
