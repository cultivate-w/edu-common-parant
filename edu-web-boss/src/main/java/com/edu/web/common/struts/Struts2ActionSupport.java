package com.edu.web.common.struts;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.edu.facade.user.common.page.PageBean;
import com.edu.facade.user.common.page.PageParam;
import com.edu.web.common.themes.dwz.DwzParam;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Struts2ActionSupport  extends ActionSupport{

	private static final long serialVersionUID = 1L;

	public PageBean pageBean;
	
	public Integer pageNum;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	public HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();
	}
	
	public String getSessionId() {
		return ServletActionContext.getRequest().getSession().getId();
	}
	
	public HttpServletResponse getHttpResponse() {
		return ServletActionContext.getResponse();
	}
	
	public Map<String, Object> getSessionMap(){
		return ActionContext.getContext().getSession();
	}
	
	public Map<String, Object> getApplicationMap(){
		return ActionContext.getContext().getApplication();
	}
	
	public String getWebRootPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}
	
	public String operateSuccess() {
		ajaxDone("200","操作成功");
		return "operateSuccess";
	}
	
	public String operateSuccess(String msg) {
		ajaxDone("200",msg);
		return "operateSuccess";
	}

	public String operateError(String message) {
		ajaxDone("300", message);
		return "operateError";
	}
	
	private void ajaxDone(String statusCode, String message) {
		DwzParam param = getDwzParam(statusCode,message);
		ActionContext.getContext().getValueStack().push(param);
	}

	private DwzParam getDwzParam(String statusCode, String message) {
		HttpServletRequest req = getHttpRequest();
		String navTabId = req.getParameter("navTabId");
		String dialogId = req.getParameter("dialogId");
		String callbackType = req.getParameter("callbackType");
		String forwardUrl = req.getParameter("forwardUrl");
		String rel = req.getParameter("rel");
		return new DwzParam(statusCode,message,navTabId,dialogId ,callbackType, forwardUrl, rel,null);
	}
	
	private int getPageNum() {
		String pageNumStr = getHttpRequest().getParameter("pageNum");
		int pageNum = 1;
		if(StringUtils.isNoneBlank(pageNumStr)) {
			pageNum = Integer.valueOf(pageNum);
		}
		return pageNum;
	}
	
	
	private int getNumPerPage() {
		String numPerPageStr = getHttpRequest().getParameter("numPerPage");
		int numPerPage = 15;
		if(StringUtils.isNoneBlank(numPerPageStr)) {
			numPerPage = Integer.parseInt(numPerPageStr);
		}
		return numPerPage;
	}
	
	public PageParam getPageParam() {
		return new PageParam(getPageNum(), getNumPerPage());
	}
	
	/**
	 * 将数据放入Struts2上下文的值栈中.<br/>
	 * ActionContext.getContext().getValueStack().push(obj);
	 */
	public void pushData(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}
	/**
	 * 将数据放入Struts2上下文中.<br/>
	 * ActionContext.getContext().put(key, value);
	 */
	public void putData(String key,Object value) {
		ActionContext.getContext().put(key, value);
	}
	
	public Double getDouble(String key) {
		String value = getHttpRequest().getParameter(key);
		if(StringUtils.isNoneBlank(value))
			return Double.parseDouble(value);
		return null;
	}
	
	public Integer getInteger(String key) {
		String value = getHttpRequest().getParameter(key);
		if(StringUtils.isNoneBlank(value))
			return Integer.parseInt(value);
		
		return null;
	}
	
	public Long getLong(String key) {
		String value = getHttpRequest().getParameter(key);
		if(StringUtils.isNoneBlank(value))
			return Long.parseLong(value);
		
		return null;
	}
	
	public String getString(String key) {
		return getHttpRequest().getParameter(key);
	}
	
	protected String lengthValidate(String propertyName,String property,boolean isRequire,int minLength,int maxLength) {
		int propertyLength = strLength(property);
		if(isRequire && propertyLength == 0) 
			return propertyName+ "不能为空，";
		else if(isRequire && minLength != 0 && propertyLength < minLength) 
			return propertyName+"不能小于"+minLength+"个字符，";
		else if(maxLength != 0 && propertyLength > maxLength) 
			return propertyName + "不能多于"+maxLength+"个字符";
		else {
			return "";
		}
	
	}

	private int strLength(String str) {
		if(StringUtils.isBlank(str)) {
			return 0;
		}
		int valueLength = 0;
		final String chinese = "[\u0391-\uFFE5]";
		for(int num = 0; num < str.length(); num++) {
			final String temp = str.substring(num, num+1);
			if(temp.matches(chinese)) {
				valueLength += 3;
			}else {
				valueLength++;
			}
		}
		return valueLength;
	}

}
