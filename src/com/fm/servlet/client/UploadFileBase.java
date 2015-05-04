package com.fm.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fm.common.ErrorConsts;
import com.fm.servlet.FMRequest;
import com.fm.servlet.FMRequestFile;
import com.fm.servlet.FMResponse;
import com.fm.servlet.ServletBase;

public abstract class UploadFileBase extends ServletBase {

	public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		FMRequestFile fmRequest = new FMRequestFile(httpServletRequest);
		FMResponse fmResponse = new FMResponse(httpServletResponse);

		int errorCode = ErrorConsts.UNKNOWN;
		String logString = httpServletRequest.getRequestURI();
		if (fmRequest.getSession().getUserId() != 0) {
			logString = logString + "	userid:" + fmRequest.getSession().getUserId();
		}
		logString = logString + "	clientVersion:" + fmRequest.getClientVersion();
		logString = logString + "	params:" + fmRequest.getClientParmas();

		try {
			errorCode = this.doSolve(fmRequest, fmResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorCode = ErrorConsts.UNKNOWN;
		}
		fmResponse.setErrorCode(errorCode);
		fmResponse.append();
		logString = logString + "	response:" + fmResponse.getResponse();
		System.out.println(logString);
	}

}
