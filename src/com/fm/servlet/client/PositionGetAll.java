package com.fm.servlet.client;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fm.collect.UserPositions;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.UserInfo;
import com.fm.data.UserPosition;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.ServletBase;

public class PositionGetAll extends ServletBase {

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		// TODO Auto-generated method stub
		
		String userid=fmRequest.getSession().getUserId();
		if(userid==null){
			return ErrorConsts.SESSION_INVALID;
		}
		
		
		FMMongo mongo=new FMMongo();
		UserPositions userPositions=new UserPositions(mongo);
		List<UserPosition> list=userPositions.getAll();
		
		JSONObject jsonResponse=fmResponse.getJsonResult();
		
		JSONArray jsonArray=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			UserPosition tmp=list.get(i);
			jsonArray.put(i, tmp.jsonForClientMap());
		}
		jsonResponse.put("userPositions", jsonArray);
		return 0;
	}

}
