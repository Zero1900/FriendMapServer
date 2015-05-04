package com.fm.servlet;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

public class FMRequest {
	private HttpServletRequest request;
	

	public FMRequest(HttpServletRequest httpServletRequest) {

		request = httpServletRequest;
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}

	public String getClientParmas() {
		return request.getParameter("params");
	}

	public String getClientVersion() {
		return request.getParameter("clientVersion");
	}

	public JSONObject getClientParamsInJson() throws JSONException {
		return new JSONObject(getClientParmas());
	}
	public FMSession getSession(){
		return new FMSession(request.getSession());
	}

	public HttpServletRequest getResuest() {
		// TODO Auto-generated method stub
		return request;
	}
}
