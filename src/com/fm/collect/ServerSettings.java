package com.fm.collect;

import com.fm.common.FMMongo;
import com.fm.data.ServerSetting;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ServerSettings {
	private FMMongo mongo;
	public ServerSettings(FMMongo mongo){
		this.mongo=mongo;
	}
	public DBCollection getCollection(){
		return mongo.getDefaultDB().getCollection("ServerSetting");
	}
	public ServerSetting getServerSetting(){
		DBCollection collection=getCollection();
		DBObject dbObject=collection.findOne();
		if (dbObject==null){
			dbObject=ServerSetting.dbObjectForDefault();
			collection.insert(dbObject);
		}
		
		ServerSetting serverSetting=new ServerSetting(dbObject);
		
		return serverSetting;
	}
}
