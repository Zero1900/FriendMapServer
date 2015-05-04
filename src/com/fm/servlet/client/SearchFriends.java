package com.fm.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fm.collect.UserInfos;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.UserInfo;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.ServletBase;

public class SearchFriends extends ServletBase {

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		request:
		{
			nickname=""
		}
		response:
		{
			[
			{friendid=1,nickname=,..},
			{..}
			]
		}
		*/
		
		
		
		JSONObject jsonRequest=fmRequest.getClientParamsInJson();
		String nickname=jsonRequest.getString("nickname");
		
		if(nickname==null){
			return ErrorConsts.REQUEST_PARAM;
		}
		
		JSONObject jsonResponse=fmResponse.getJsonResult();
		
		FMMongo mongo=new FMMongo();
		UserInfos userInfos=new UserInfos(mongo);
		JSONArray jsonArray=new JSONArray();
		List<UserInfo> list=userInfos.searchFriends(nickname);
		Iterator<UserInfo> it=list.iterator();
		while(it.hasNext()){
			jsonArray.put(it.next().createJsonForShow());
		}
		jsonResponse.put("friends", jsonArray);
		return 0;
	}

}
