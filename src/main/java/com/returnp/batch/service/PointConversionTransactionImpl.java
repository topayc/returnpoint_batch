package com.returnp.batch.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.returnp.batch.dao.PointConversionTransactionDao;
import com.returnp.batch.model.PointConversionTransaction;
import com.returnp.batch.service.interfaces.PointConversionTransactionService;

@Service("pointConversionTransactionService")
public class PointConversionTransactionImpl implements PointConversionTransactionService {

	@Resource(name = "pointConversionTransactionDao")
    protected PointConversionTransactionDao dao;
	
	@Override
	@Transactional
	public int updateByStatus() {
		// TODO Auto-generated method stub
		return dao.updateByStatus();
	}

}
