package com.fm.data;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ClientVersion {
	public int versionCode=0;				//1(versionCode)
	public boolean forceUpdate=false;	//true/false
	public String versionName="0.0";	//"1.0"
	public ClientVersion(){
		
	}
	public ClientVersion(int versionCode,boolean forceUpdate,String versionName){
		this.versionCode=versionCode;
		this.forceUpdate=forceUpdate;
		this.versionName=versionName;
	}
	public ClientVersion(DBObject dbObject){
		parseDBObject(dbObject);
	}
	/**
	 * 解析dbObject
	 * @param dbObject
	 */
	public void parseDBObject(DBObject dbObject){
		versionCode=(int) dbObject.get("_id");
		forceUpdate=(boolean) dbObject.get("forceUpdate");
		versionName=(String)dbObject.get("versionName");
	}
	/**
	 * 创建dbObject
	 * @return
	 */
	public DBObject createDbObject(){
		DBObject dbObject=new BasicDBObject();
		dbObject.put("_id", versionCode);
		dbObject.put("forceUpdate", forceUpdate);
		dbObject.put("versionName", versionName);
		return dbObject;
	}
	/**
	 * 创建json
	 * @return
	 * @throws JSONException
	 */
	public JSONObject createJsonObject() throws JSONException{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("versionCode", versionCode);
		jsonObject.put("versionName", versionName);
		return jsonObject;
	}
}
