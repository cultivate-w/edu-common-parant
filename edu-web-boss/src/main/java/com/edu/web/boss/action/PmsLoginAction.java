package com.edu.web.boss.action;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.edu.common.web.constant.SessionConstant;
import com.edu.facade.user.entity.PmsUser;
import com.edu.facade.user.enums.UserStatusEnum;
import com.edu.facade.user.enums.UserTypeEnum;
import com.edu.facade.user.service.PmsUserFacade;
import com.edu.web.boss.base.BaseAction;

@Scope("prototype")
public class PmsLoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PmsUserFacade pmsUserFacade;

	public String loginPage() {
		return "login";
	}
	
	public String userLogin() {
		try {
		String userNo = getString("userNo");
		if(StringUtils.isBlank(userNo)) {
			this.putData("userNo", "用户名不能为空");
			return "input";
		}
		this.putData("userNo",userNo);
		PmsUser user = pmsUserFacade.findUserByUserNo(userNo);
		if(user == null) {
			this.putData("userNoMsg", "用户或密码不正确");
			return "input";
		}
		if(user.getStatus().intValue() == UserStatusEnum.INACTIVE.getValue()) {
			this.putData("userNoMsg", "该账号已经被冻结");
			return "input";
		}
		String pwd = getString("userPwd");
		if(StringUtils.isBlank(pwd)) {
			this.putData("userPwdMsg", "密码不能为空");
			return "input";
		}
		if(user.getUserPwd().equals(DigestUtils.sha1Hex(pwd))) {
			this.getSessionMap().put(SessionConstant.USER_SESSION_KEY, user);
			if(UserTypeEnum.MAIN_USER.getValue().equals(user.getUserType())) {
				this.getSessionMap().put(SessionConstant.MAIN_USER_ID_SESSION_KEY, user.getId());
			}else if(UserTypeEnum.SUB_USER.getValue().equals(user.getUserType())) {
				this.getSessionMap().put(SessionConstant.MAIN_USER_ID_SESSION_KEY, user.getMainUserId());
			}else {
				this.getSessionMap().put(SessionConstant.MAIN_USER_ID_SESSION_KEY, 0L);
			}
			this.putData("userNo",userNo);
			this.putData("lastLoginTime", user.getLastLoginTime());
			
			try {
				user.setLastLoginTime(new Date());
				user.setPwdErrorCount(0);
				pmsUserFacade.update(user);
			} catch (Exception e) {
				e.printStackTrace();
				this.putData("errorMsg", e.getMessage());
				return "input";
			}
			this.putData("isChangePwd", user.getIsChangedPwd());
			return "main";
		}else {
			Integer pwdErrorCount = user.getPwdErrorCount();
			if(pwdErrorCount == null) {
				pwdErrorCount = 0;
			}
			user.setPwdErrorCount(pwdErrorCount+1);
			user.setPwdErrorTime(new Date());
			String msg = "";
			
			if(user.getPwdErrorCount().intValue() >= SessionConstant.WEB_PWD_INPUT_ERROR_LIMIT) {
				user.setStatus(UserStatusEnum.INACTIVE.getValue());
				msg += "<br/>密码已连续输错【" + SessionConstant.WEB_PWD_INPUT_ERROR_LIMIT + "】次，帐号已被冻结";
			}else {
				msg += "<br/>密码错误，再输错【" + (SessionConstant.WEB_PWD_INPUT_ERROR_LIMIT - user.getPwdErrorCount().intValue()) + "】次将冻结帐号";
			}
			pmsUserFacade.update(user);
			this.putData("userPwdMsg", msg);
			return "input";
		}
		
		} catch (Exception e) {
			this.putData("errorMsg", "登录出错");
			return "input";
		}
	}
	
	public String logoutConfirm() {
		return "logoutConfirm";
	}
	
	public String logout() {
		this.getSessionMap().clear();
		return "logout";
	}
	public String timeoutConfirm() {
		this.getSessionMap().clear();
		return "timeoutConfirm";
	}
	
	
}
