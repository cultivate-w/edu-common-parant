package com.edu.service.user.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.facade.user.common.exceptions.BizException;
import com.edu.facade.user.common.page.PageBean;
import com.edu.facade.user.common.page.PageParam;
import com.edu.facade.user.entity.PmsUser;
import com.edu.service.user.dao.PmsUserDao;

@Service("pmsUserBiz")
public class PmsUserBiz {
	
	@Autowired
	private PmsUserDao pmsUserDao;
	
	public void create(PmsUser pmsUser) {
		pmsUserDao.insert(pmsUser);
	}
	
	public PmsUser getById(Long userId) {
		return pmsUserDao.getById(userId);
	}
	
	public PmsUser findUserByUserNo(String userNo) {
		return pmsUserDao.findByUserNo(userNo);
	}
	
	public void deleteUserById(long userId) {
		PmsUser pmsUser = pmsUserDao.getById(userId);
		if(pmsUser != null) {}
		if("1".equals(pmsUser.getUserType())) {
			throw new BizException("【" + pmsUser.getUserNo() + "】为超级管理员，不能删除！");
		}
		pmsUserDao.deleteById(pmsUser.getId());
	}
	
	public void update(PmsUser user) {
		pmsUserDao.update(user);
	}
	
	public void updateUserPwd(Long userId,String newPwd,boolean isTrue) {
		PmsUser pmsUser = pmsUserDao.getById(userId);
		pmsUser.setUserPwd(newPwd);
		pmsUser.setPwdErrorCount(0);
		pmsUser.setIsChangedPwd(isTrue);
		pmsUserDao.update(pmsUser);
	}
	
	public PageBean listPage(PageParam pageParam ,Map<String, Object> paramMap) {
		return pmsUserDao.list(pageParam, paramMap);
	}


	
	
	
}
