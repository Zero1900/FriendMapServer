package com.fm.servlet.client;


import org.json.JSONException;
import org.json.JSONObject;

import com.fm.collect.UserInfos;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.UserInfo;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.ServletBase;

public class Register extends ServletBase {

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		//解析json
		JSONObject jsonRequest=null;
		String s=fmRequest.getClientParmas();
		jsonRequest=new JSONObject(s);
		try {
			jsonRequest=fmRequest.getClientParamsInJson();
		} catch (JSONException e) {
			return ErrorConsts.JSON_PARSE;
		}
		//获取参数
		String username=null,nickname=null,password=null;
		try {
			username=jsonRequest.getString("username");
			nickname=jsonRequest.getString("nickname");
			password=jsonRequest.getString("password");
		} catch (Exception e) {
			return ErrorConsts.REQUEST_PARAM;
		}
		
		
		//用户名合法
		if(username.length()<6 || username.length()>20){
			return ErrorConsts.USERNAME_ILLEGAL;
		}
		
		
		FMMongo mongo=new FMMongo();
		UserInfos userInfos=new UserInfos(mongo);
		
		//用户名存在
		if(userInfos.getDataByName(username)!=null){
			return ErrorConsts.USERNAME_EXIST;
		}
		
		//创建新用户
		UserInfo userInfo=new UserInfo(username, password, nickname);
		
		userInfos.insertNewUser(userInfo);
		
		return ErrorConsts.NOERROR;
	}

}
