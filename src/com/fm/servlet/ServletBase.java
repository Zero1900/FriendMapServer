package com.fm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.fm.common.ErrorConsts;
import com.fm.common.FMMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;


public abstract class ServletBase extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletBase() {
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
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		
		FMRequest fmRequest=new FMRequest(httpServletRequest);
		FMResponse fmResponse=new FMResponse(httpServletResponse);
		
		int errorCode=ErrorConsts.UNKNOWN;
		String logString=""+httpServletRequest.getRequestURI();
		if(fmRequest.getSession().getUserId()!=0){
			logString=logString+"	userid:"+fmRequest.getSession().getUserId();
		}
		logString=logString+"	clientVersion:"+fmRequest.getClientVersion();
		logString=logString+"	params:"+fmRequest.getClientParmas();
		
		try {
			
			errorCode=this.doSolve(fmRequest,fmResponse);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorCode=ErrorConsts.UNKNOWN;
		}
		fmResponse.setErrorCode(errorCode);
		fmResponse.append();
		logString=logString+"	response:"+fmResponse.getResponse();
		System.out.println(logString);
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

	public abstract int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception;
}
