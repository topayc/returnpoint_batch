package com.returnp.batch.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.batch.model.PointConversionTransaction;

@Transactional
public interface PointConversionTransactionService {
	  int updateByStatus();
}
