package com.fm.common;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class FMMongo extends Mongo{

	public FMMongo() throws UnknownHostException {
		super();
		// TODO Auto-generated constructor stub
	}
	public DB getDefaultDB(){
		return getDB("FriendMap");
	}
}
