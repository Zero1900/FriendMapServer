package com.fm.collect;

import javax.management.Query;

import com.fm.common.FMMongo;
import com.fm.data.FriendInfo;
import com.mongodb.DBCollection;
import com.mongodb.QueryBuilder;

public class FriendInfos {
	private FMMongo mongo;
	public FriendInfos(FMMongo mongo){
		this.mongo=mongo;
	}
	
	public DBCollection getCollection(){
		return mongo.getDefaultDB().getCollection("FriendInfo");
	}
	
	public void insertNewFriends(int user1,int user2){
		DBCollection collection=getCollection();
		
		FriendInfo friendInfo1=new FriendInfo(user1);
		FriendInfo friendInfo2=new FriendInfo(user2);
		
		collection.insert(friendInfo1.createDBObject(),friendInfo2.createDBObject());
		
	}
	
}
