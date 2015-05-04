package com.fm.data;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class FriendState {
	public int friendid;
	public boolean authorize,authorized;
	public FriendState(int friendid){
		this.friendid=friendid;
		authorize=false;
		authorized=false;
	}
	public FriendState(DBObject dbObject){
		parseDBObject(dbObject);
	}
	public void parseDBObject(DBObject dbObject){
		friendid=(int)dbObject.get("friendid");
		authorize=(boolean)dbObject.get("authorize");
		authorized=(boolean)dbObject.get("authorized");
	}
	public DBObject createDbObject(){
		DBObject dbObject=new BasicDBObject();
		
		dbObject.put("friendid", friendid);
		dbObject.put("authorize", authorize);
		dbObject.put("authorized", authorized);
		return dbObject;
	}
	public JSONObject createJsonObject() throws JSONException{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("friendid", friendid);
		jsonObject.put("authorize", authorize);
		jsonObject.put("authorized", authorized);
		return jsonObject;
	}
}
