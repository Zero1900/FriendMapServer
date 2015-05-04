package com.fm.servlet.client;

import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

import com.fm.collect.ServerSettings;
import com.fm.collect.UserPositions;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.ServerSetting;
import com.fm.data.UserPosition;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.FMSession;
import com.fm.servlet.ServletBase;

public class CommitPosition extends ServletBase {

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		// TODO Auto-generated method stub
		//获取用户
		int userid=fmRequest.getSession().getUserId();
		if(userid == 0){
			return ErrorConsts.SESSION_INVALID;
		}
		//解析json
		JSONObject jsonRequest = null;
		try {
			jsonRequest = fmRequest.getClientParamsInJson();
		} catch (JSONException e) {
			// TODO: handle exception
			return ErrorConsts.JSON_PARSE;
		}
		//解析参数获得经纬度
		double longitude=0,latitude=0;
		long time=0;
		try {
			longitude=jsonRequest.getDouble("longitude");
			latitude=jsonRequest.getDouble("latitude");
		} catch (Exception e) {
			// TODO: handle exception
			return ErrorConsts.REQUEST_PARAM;
		}
		//获取当前时间
		Calendar calendar=Calendar.getInstance();
		time=calendar.getTimeInMillis();
		
		//更新服务器数据
		UserPosition userPosition=new UserPosition(userid, longitude, latitude, time);
		
		FMMongo mongo=new FMMongo();
		UserPositions userPositions=new UserPositions(mongo);
		
		userPositions.updatePosition(userPosition);
		
		
		//获取设置信息
		ServerSettings serverSettings=new ServerSettings(mongo);
		ServerSetting serverSetting=serverSettings.getServerSetting();
		//返回客户端定位设置
		JSONObject jsonObject=serverSetting.jsonForClientLocation();
		JSONObject jsonResponce=fmResponse.getJsonResult();
		jsonResponce.put("locationSetting", jsonObject);
		return 0;
	}

}
