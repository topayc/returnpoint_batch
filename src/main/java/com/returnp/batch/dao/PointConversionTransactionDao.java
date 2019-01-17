package com.returnp.batch.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.returnp.batch.dao.mapper.PointConversionTransactionMapper;
import com.returnp.batch.model.PointConversionTransaction;

@Repository
public class PointConversionTransactionDao{

	@Resource(name="pointConversionTransactionMapper")
	private PointConversionTransactionMapper mapper;
	
	public int updateByStatus() {
		// TODO Auto-generated method stub
		return this.mapper.updateByStatus();
	};
}