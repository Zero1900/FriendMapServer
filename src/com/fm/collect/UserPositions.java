package com.fm.collect;

import java.util.ArrayList;
import java.util.List;

import com.fm.common.FMMongo;
import com.fm.data.UserPosition;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserPositions {
	private FMMongo mongo;

	public UserPositions(FMMongo mongo) {
		this.mongo = mongo;
	}

	/**
	 * 获取聚集
	 */
	public DBCollection getCollection() {
		return mongo.getDefaultDB().getCollection("UserPosition");
	}

	/**
	 * 修改数据
	 */
	public void updatePosition(UserPosition userPosition) {
		DBCollection collection = getCollection();
		DBObject queryDbObject = new BasicDBObject("_id", userPosition.userid);
		collection.update(queryDbObject, userPosition.createUpdateDbObject(), true, false);
	}

	/**
	 * 查找数据
	 */
	public UserPosition getPosition(String userid) {
		DBCollection collection = getCollection();
		DBObject dbObject = collection.findOne(new BasicDBObject("_id", userid));
		if (dbObject != null) {
			UserPosition userPosition = new UserPosition(dbObject);
			return userPosition;
		}else {
			return null;
		}
	}
	public List<UserPosition> getAll(){
		List<UserPosition> list=new ArrayList<UserPosition>();
		
		DBCollection collection=getCollection();
		DBCursor cursor=collection.find();
		
		while(cursor.hasNext()){
			DBObject dbObject=cursor.next();
			UserPosition tmp=new UserPosition(dbObject);
			list.add(tmp);
		}
		
		return list;
	}
}
