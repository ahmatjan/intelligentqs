package cn.com.mq;

import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;

public class FollowObserve implements Observe{
	
	@Override
	public void update(int followingid, String str){
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		String mq = "userid:" + followingid + ":mq";
		rdb.lpush(mq, str);
	}
}
