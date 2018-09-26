package com.edu.service.user.dao;

import com.edu.facade.user.entity.PmsUser;
import com.edu.service.user.common.core.dao.BaseDao;

public interface PmsUserDao extends BaseDao<PmsUser> {
	
	PmsUser findByUserNo(String userNo);

}
