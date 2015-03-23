package com.fm.servlet;

import javax.servlet.http.HttpSession;

public class FMSession {
	private HttpSession session;
	public FMSession(HttpSession session) {
		this.session=session;
	}
	public void setLoginInfo(String userid){
		session.setAttribute("userid", userid);
	}
	public String getUserId(){
		return (String) session.getAttribute("userid");
	}
	public String getKey(){
		return session.getId();
	}
}
