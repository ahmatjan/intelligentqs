package cn.com.mq;

import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;

public class PraiseObserve implements Observe{
	
	@Override
	public void update(int userid, String str) {
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		String mq = "userid:" + userid + ":mq";
		rdb.lpush(mq, str);
	}
}
