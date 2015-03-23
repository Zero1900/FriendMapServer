package com.fm.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fm.collect.ClientVersions;
import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.fm.data.ClientVersion;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMResponse;
import com.fm.servlet.ServletBase;

public class CheckVersion extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public CheckVersion() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		/*
		request:
			clientVersion
		response:
		{
			forceUpdate=true
			maxVersion={
				versionCode=0,
				versionName="0.0"
			}
		}	
		 */
		

		
		FMMongo mongo = new FMMongo();
		ClientVersions clientVersions=new ClientVersions(mongo);
		ClientVersion tmpClientVersion=clientVersions.getData(Integer.valueOf(fmRequest.getClientVersion()));
		
		//强更信息
		JSONObject jsonObject=fmResponse.getJsonResult();
		jsonObject.put("forceUpdate", tmpClientVersion.forceUpdate);
		
		//最新版本信息
		ClientVersion maxClientVersion=clientVersions.getMaxVersion();
		JSONObject jsonMax=maxClientVersion.createJsonObject();
		jsonObject.put("maxVersion", jsonMax);
		
		return ErrorConsts.NOERROR;
	}
}
