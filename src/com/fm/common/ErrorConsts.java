package com.fm.common;

public final class ErrorConsts {
	public static final int NOERROR = 0;	//没有错误
	
	public static final int SESSION_INVALID=70; //无效的session
	
	public static final int UNKNOWN = 100;//未知错误
	public static final int JSON_PARSE=101;//JSON解析失败
	public static final int REQUEST_PARAM=102;//参数错误
	
	public static final int USERNAME_ILLEGAL=201;//非法用户名
	public static final int USERNAME_EXIST=202;//用户名已存在
	
	public static final int USERNAME_NOTEXIST=210;//用户名不存在
	public static final int PASSWORD_DISAGREE=211;//密码不一致
}
