package com.fm.collect;


import com.fm.common.FMMongo;
import com.fm.data.UserInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserInfos {
	private FMMongo mongo;

	public UserInfos(FMMongo mongo) {
		this.mongo = mongo;
	}

	/**
	 * 获取聚集
	 * 
	 * @return
	 */
	public DBCollection getCollection() {
		return mongo.getDefaultDB().getCollection("UserInfo");
	}
	
	public boolean insertNewUser(UserInfo userInfo){
		
		DBCollection collection=getCollection();
		DBCursor cursor=collection.find().sort(new BasicDBObject("$natural",-1)).limit(1);
		if(cursor.hasNext()){
			userInfo.userid=""+(Integer.parseInt(cursor.next().get("_id").toString())+1);
		}else{
			userInfo.userid="0";
		}
		collection.insert(userInfo.createDbObject());
		return true;
	}
	
	/**
	 * 根据用户id获取数据
	 * 
	 * @param username
	 * @return
	 */
	public UserInfo getDataById(String userid) {
		DBCollection collection = getCollection();
		DBObject dbObject = collection.findOne(new BasicDBObject("_id", userid));
		if (dbObject != null) {
			return new UserInfo(dbObject);
		} else {
			return null;
		}
	}

	/**
	 * 根据用户名获取数据
	 * 
	 * @param username
	 * @return
	 */
	public UserInfo getDataByName(String username) {
		DBCollection collection = getCollection();
		DBObject dbObject = collection.findOne(new BasicDBObject("username", username));
		if (dbObject != null) {
			return new UserInfo(dbObject);
		} else {
			return null;
		}
	}
}
