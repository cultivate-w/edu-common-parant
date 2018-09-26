package com.edu.web.common.themes.dwz;

public class DwzParam {

	/**
	 * Ajax请求的执行状态码.<br/>
	 * statusCode:{ok:200, error:300, timeout:301}.<br/>
	 * 200：成功，300：错误，301:请求超时.
	 */
	private String statusCode;

	/**
	 * Ajax提示消息 message 
	 */
	private String message;
	/**
	 * navTabId. 
	 * 服务器传回navTabId可以把那个navTab标记为reloadFlag=1,下次切换到那个navTab时会重新载入内容 .
	 */
	private String navTabId;
	
	/**
	 * dialogId. 
	 * 服务器传回dialogId可以把那个dialogId标记为reloadFlag=1,下次切换到那个dialog时会重新载入内容 .
	 */
	private String dialogId;
	/**
	 * Ajax请求回调类型. <br/>
	 * callbackType如果是closeCurrent就会关闭当前tab选项, 只有callbackType="forward"时需要forwardUrl值,以重定向到另一个URL.
	 */
	private String callbackType;
	/** 
	 * 重定向URL . 
	 */
	private String forwardUrl;

	/**
	 * 关联Action .
	 */
	private String rel;
	
	private String confirmMsg;
	
	
	
	public DwzParam(String statusCode,String message,String navTabId,String dialogId,String callbackType,String forwardUrl,String rel,String confirmMsg) {
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.dialogId = dialogId;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
		this.rel = rel;
		this.confirmMsg = confirmMsg;
	}
	
	
	/**
	 * DwzAjaxDone所需参数的构造函数.
	 * @param navTabId .
	 * @param callbackType .
	 * @param forwardUrl .
	 * @param rel .
	 */
	public DwzParam(String navTabId, String callbackType, String forwardUrl, String rel) {
		this.navTabId = navTabId;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
		this.rel = rel;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getNavTabId() {
		return navTabId;
	}


	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}


	public String getDialogId() {
		return dialogId;
	}


	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}


	public String getCallbackType() {
		return callbackType;
	}


	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}


	public String getForwardUrl() {
		return forwardUrl;
	}


	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}


	public String getRel() {
		return rel;
	}


	public void setRel(String rel) {
		this.rel = rel;
	}


	public String getConfirmMsg() {
		return confirmMsg;
	}


	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	
	
}
