package com.fm.collect;

import com.fm.common.FMMongo;
import com.fm.data.ClientVersion;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ClientVersions {
	private FMMongo mongo;

	public ClientVersions(FMMongo mongo) {
		// TODO Auto-generated constructor stub
		this.mongo = mongo;
	}

	/** 获取聚集 */
	public DBCollection getCollection() {
		return mongo.getDefaultDB().getCollection("ClientVersion");
	}

	/** 获取版本信息 */
	public ClientVersion getData(int _id) {
		DBCollection collection = getCollection();
		DBObject dbObject = collection.findOne(new BasicDBObject("_id", _id));
		if (dbObject != null) {
			return new ClientVersion(dbObject);
		} else {
			return null;
		}
	}

	/** 获取最新版本信息 */
	public ClientVersion getMaxVersion() {
		ClientVersion maxClientVersion = new ClientVersion();
		ClientVersion tmpClientVersion = new ClientVersion();

		DBCollection collection = getCollection();
		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
			DBObject dbObject = (DBObject) cursor.next();
			tmpClientVersion.parseDBObject(dbObject);
			if (tmpClientVersion.versionCode > maxClientVersion.versionCode) {
				maxClientVersion.parseDBObject(dbObject);
			}
		}

		return maxClientVersion;
	}

}
