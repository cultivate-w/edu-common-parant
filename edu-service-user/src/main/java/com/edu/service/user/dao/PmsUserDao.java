package com.edu.service.user.dao;

import com.edu.common.core.BaseDao;
import com.edu.facade.user.entity.PmsUser;

public interface PmsUserDao extends BaseDao<PmsUser> {
	
	PmsUser findByUserNo(String userNo);

}
