package com.fm.servlet.client;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.fm.collect.UserInfos;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.UserInfo;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.FMSession;
import com.fm.servlet.ServletBase;

public class Login extends ServletBase {

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		// 解析json
		JSONObject jsonRequest = null;
		try {
			jsonRequest = fmRequest.getClientParamsInJson();
		} catch (JSONException e) {
			return ErrorConsts.JSON_PARSE;
		}
		// 获取参数
		String username = null,password = null;
		try {
			username = jsonRequest.getString("username");
			password = jsonRequest.getString("password");
		} catch (Exception e) {
			return ErrorConsts.REQUEST_PARAM;
		}
		
		FMMongo mongo=new FMMongo();
		UserInfos userInfos=new UserInfos(mongo);
		//查找用户
		UserInfo userInfo=userInfos.getDataByName(username);
		if(userInfo==null){
			return ErrorConsts.USERNAME_NOTEXIST;
		}
		//校验密码
		if(!userInfo.password.equals(password)){
			return ErrorConsts.PASSWORD_DISAGREE;
		}
		
		FMSession session=fmRequest.getSession(); 
		String sessionKey=session.getKey();
		JSONObject jsonResponse=fmResponse.getJsonResult();
		jsonResponse.put("session", sessionKey);
		
		session.setLoginInfo(userInfo.userid);
		
		return ErrorConsts.NOERROR;
	}

}
