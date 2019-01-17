package com.returnp.batch.dto;

import java.util.ArrayList;

public class SearchListResponse<T> extends BaseResponse{
	public int total;
	public ArrayList<T> rows;

	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<T> getRows() {
		return rows;
	}
	public void setRows(ArrayList<T> rows) {
		this.rows = rows;
		
		/*if(rows != null && rows.size()>0) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++" + (rows.get(0) instanceof QueryCondition));
			if( rows.get(0) instanceof QueryCondition) {
				this.total = ((QueryCondition)rows.get(0)).getTotal();
			}
		}*/
	}
}
