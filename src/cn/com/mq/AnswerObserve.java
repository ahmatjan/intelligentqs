package cn.com.mq;

import cn.com.util.RUtil;
import redis.clients.jedis.Jedis;

public class AnswerObserve implements Observe{

	@Override
	public void update(int userid, String str) {
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		String mq = "userid:" + userid + ":mq";
		rdb.lpush(mq, str);
	}
	
}
