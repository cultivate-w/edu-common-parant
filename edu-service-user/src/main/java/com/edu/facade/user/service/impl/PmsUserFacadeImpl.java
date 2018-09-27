package com.edu.facade.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.common.page.PageBean;
import com.edu.common.page.PageParam;
import com.edu.facade.user.entity.PmsUser;
import com.edu.facade.user.service.PmsUserFacade;
import com.edu.service.user.biz.PmsUserBiz;

@Service("pmsUserFacade")
public class PmsUserFacadeImpl implements PmsUserFacade{

	@Autowired
	private PmsUserBiz pmsUserBiz;
	
	@Override
	public void create(PmsUser pmsUser) {
		pmsUserBiz.create(pmsUser);
	}

	@Override
	public PmsUser getById(Long userId) {
		return pmsUserBiz.getById(userId);
	}

	@Override
	public PmsUser findUserByUserNo(String userNo) {
		return pmsUserBiz.findUserByUserNo(userNo);
	}

	@Override
	public void deleteUserById(long userId) {
		pmsUserBiz.deleteUserById(userId);
	}

	@Override
	public void update(PmsUser user) {
		pmsUserBiz.update(user);
	}

	@Override
	public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
		pmsUserBiz.updateUserPwd(userId, newPwd, isTrue);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return pmsUserBiz.listPage(pageParam, paramMap);
	}

}
