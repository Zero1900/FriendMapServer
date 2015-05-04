package com.fm.servlet.client;

import java.io.File;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.fm.servlet.FMRequest;
import com.fm.servlet.FMRequestFile;
import com.fm.servlet.FMResponse;

public class UploadFile extends UploadFileBase {


	@Override
	public int doSolve(FMRequest fmRequest, FMResponse fmResponse) throws Exception {
		// TODO Auto-generated method stub
		FMRequestFile fmRequestFile=(FMRequestFile)fmRequest;
		
		FileItem fileItem=fmRequestFile.getFileItem();
		
		String path=fmRequestFile.getSession().getSession().getServletContext().getRealPath("/")+"/Image";
		
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		File file=new File(path, "new.png");
		file.createNewFile();
		fileItem.write(file);
		
		return 0;
	}}
