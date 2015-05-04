package com.fm.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fm.collect.UserInfos;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.UserInfo;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.FMSession;
import com.fm.servlet.ServletBase;

public class GetUserInfo extends ServletBase {

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		// TODO Auto-generated method stub
		FMSession session=fmRequest.getSession();
		int userid=session.getUserId();
		
		if(userid==0){
			return ErrorConsts.SESSION_INVALID;
		}
		
		FMMongo mongo =new FMMongo();
		UserInfos userInfos=new UserInfos(mongo);
		UserInfo userInfo=userInfos.getDataById(userid);
		
		JSONObject jsonResponse=fmResponse.getJsonResult();
		
		jsonResponse.put("userInfo", userInfo.createJsonForShow());
		
		return 0;
	}

}
