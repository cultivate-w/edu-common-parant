package com.edu.service.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.edu.common.core.BaseDaoImpl;
import com.edu.facade.user.entity.PmsUser;
import com.edu.service.user.dao.PmsUserDao;
@Repository("pmsUserDao")
public class PmsUserDaoImpl extends BaseDaoImpl<PmsUser> implements PmsUserDao{

	@Override
	public PmsUser findByUserNo(String userNo) {
		return super.getSqlSession().selectOne(getStatement("findByUserNo"),userNo);
	}
	

}
