package cn.com.util;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import redis.clients.jedis.Jedis;  

public class RUtil{
	
	public static Jedis con(){
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		return jedis;
	}
}