package com.fm.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.json.JSONException;
import org.json.JSONObject;

public class FMRequestFile extends FMRequest {
	private Map<String, FileItem> map=new HashMap<String, FileItem>();
	public FMRequestFile(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
		// TODO Auto-generated constructor stub
		parseFiles();
	}
	
	private void parseFiles(){
		HttpServletRequest request=getResuest();
		
		String temp = request.getSession().getServletContext().getRealPath("/") + "Temp"; // 临时目录
		System.out.println("temp=" + temp);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);// 设置最多只允许在内存中存储的数据,单位:字节
		factory.setRepository(new File(temp));
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(1 * 1024 * 1024); // 设置允许用户上传文件大小,单位:字节
		// 开始读取上传信息
		List fileItems = null;

		try {
			fileItems = upload.parseRequest(new ServletRequestContext(request));
			System.out.println("fileItems=" + fileItems);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Iterator iter = fileItems.iterator(); // 依次处理每个上传的文件
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();// 忽略其他不是文件域的所有表单信息
			map.put(item.getFieldName(), item);
		}
	}
	public String getClientParmas() {
		return map.get("params").getString();
	}

	public String getClientVersion() {
		return map.get("clientVersion").getString();
	}

	public FileItem getFileItem(){
		return map.get("file");
	}
	
	public JSONObject getClientParamsInJson() throws JSONException {
		return new JSONObject(getClientParmas());
	}
}
