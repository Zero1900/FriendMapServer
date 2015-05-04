package com.fm.servlet;

import javax.servlet.http.HttpSession;

public class FMSession {
	private HttpSession session;
	public FMSession(HttpSession session) {
		this.session=session;
	}
	public void setLoginInfo(int userid){
		session.setAttribute("userid", userid);
	}
	public int getUserId(){
		if(session.getAttribute("userid")==null){
			return 0;
		}
		return (int) session.getAttribute("userid");
	}
	public String getKey(){
		return session.getId();
	}
	public HttpSession getSession(){
		return session;
	}
}
