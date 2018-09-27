package com.edu.web.boss.base;

import com.edu.common.web.constant.SessionConstant;
import com.edu.common.web.struts.Struts2ActionSupport;
import com.edu.facade.user.entity.PmsUser;

@SuppressWarnings("serial")
public class BaseAction extends Struts2ActionSupport implements UserLoginedAware{

	@Override
	public PmsUser getLoginedUser() {
		PmsUser user = (PmsUser) getSessionMap().get(SessionConstant.USER_SESSION_KEY);
		return user;
	}

}
