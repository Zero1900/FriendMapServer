package com.fm.data;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserPosition {
	public int userid;
	public double longitude;
	public double latitude;
	public long time;
	public UserPosition(int userid,double longitude,double latitude,long time){
		this.userid=userid;
		this.longitude=longitude;
		this.latitude=latitude;
		this.time=time;
	}
	public UserPosition(DBObject dbObject){
		parseDBObject(dbObject);
	}
	public void parseDBObject(DBObject dbObject){
		userid=(int) dbObject.get("_id");
		longitude=(double) dbObject.get("longitude");
		latitude=(double) dbObject.get("latitude");
		time=(long) dbObject.get("time");
	}
	public DBObject createDbObject(){
		DBObject dbObject=new BasicDBObject();
		dbObject.put("_id", userid);
		dbObject.put("longitude", longitude);
		dbObject.put("latitude", latitude);
		dbObject.put("time", time);
		return dbObject;
	}
	public DBObject createUpdateDbObject(){
		DBObject dbObject=new BasicDBObject();
		dbObject.put("longitude", longitude);
		dbObject.put("latitude", latitude);
		dbObject.put("time", time);
		return dbObject;
	}
	public JSONObject jsonForClientMap(){
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("userid", userid);
			jsonObject.put("longitude", longitude);
			jsonObject.put("latitude", latitude);
			jsonObject.put("time", time);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return jsonObject;
	}
}
