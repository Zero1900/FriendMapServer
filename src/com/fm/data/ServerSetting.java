package com.fm.data;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ServerSetting {
	public long clientTimeLocaleInterval;
	public long clientTimeCommitInterval;

	public ServerSetting(DBObject dbObject) {
		clientTimeCommitInterval = (long) dbObject.get("clientTimeCommitInterval");
		clientTimeLocaleInterval = (long) dbObject.get("clientTimeLocaleInterval");
	}

	public static DBObject dbObjectForDefault() {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("clientTimeLocaleInterval", (long)30000);
		dbObject.put("clientTimeCommitInterval", (long)30000);
		return dbObject;
	}

	public JSONObject jsonForClientLocation() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("timeLocaleInterval", clientTimeLocaleInterval);
			jsonObject.put("timeCommitInterval", clientTimeCommitInterval);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonObject;
	}
}
