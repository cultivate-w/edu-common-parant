package com.edu.web.boss.base;

import com.edu.facade.user.entity.PmsUser;
import com.edu.web.common.constant.SessionConstant;
import com.edu.web.common.struts.Struts2ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends Struts2ActionSupport implements UserLoginedAware{

	@Override
	public PmsUser getLoginedUser() {
		PmsUser user = (PmsUser) getSessionMap().get(SessionConstant.USER_SESSION_KEY);
		return user;
	}

}
