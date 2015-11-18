package com.hdsx.ao.dao;

import com.hdsx.ao.bean.HDFeatures;
import com.hdsx.ao.exception.HDException;
import com.hdsx.ao.parameter.DeleteParameter;
import com.hdsx.ao.parameter.InsertParameter;
import com.hdsx.ao.parameter.QueryParameter;
import com.hdsx.ao.parameter.UpdateParameter;

public class DaoProxy implements AoDao{

	private AoDao dao;
	public DaoProxy(AoDao dao){
		this.dao = dao;
	}
	@Override
	public HDFeatures query(QueryParameter parameter) throws HDException {
		// TODO Auto-generated method stub
		dao.getClass().getInterfaces()[0].getAnnotation(null);
		return null;
	}

	@Override
	public int update(UpdateParameter parameter) throws HDException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(InsertParameter parameter) throws HDException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(DeleteParameter parameter) throws HDException {
		// TODO Auto-generated method stub
		return 0;
	}

}
