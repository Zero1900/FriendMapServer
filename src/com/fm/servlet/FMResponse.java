package com.fm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class FMResponse {
	private JSONObject jsonObject;
	private HttpServletResponse response;
	public FMResponse(HttpServletResponse response) {
		this.response=response;
		
		jsonObject=new JSONObject();
		
		try {
			jsonObject.put("result", new JSONObject());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setErrorCode(-1);
	}
	public void setErrorCode(int errorCode){
		try {
			jsonObject.put("errorCode", errorCode);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JSONObject getJsonResult(){
		try {
			return jsonObject.getJSONObject("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void append(){
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
