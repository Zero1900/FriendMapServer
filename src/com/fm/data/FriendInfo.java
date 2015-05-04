package com.fm.data;

import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class FriendInfo {
	public int userid;
	public List<FriendState> list;

	public FriendInfo(int userid){
		this.userid=userid;
	}
	public FriendInfo(DBObject dbObject){
		parseDBObject(dbObject);
	}
	public void addFriend(){
		
	}
	public void parseDBObject(DBObject dbObject){
		userid=(int)dbObject.get("_id");
		BasicDBList dblist=(BasicDBList) dbObject.get("list");
		Iterator<Object> it=dblist.iterator();
		while(it.hasNext()){
			list.add(new FriendState((DBObject)it.next()));
		}
	}
	public DBObject createDBObject(){
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", userid);
		BasicDBList dbList=new BasicDBList();
		dbObject.put("list", dbList);
		return dbObject;
	}
	
	
	
}
