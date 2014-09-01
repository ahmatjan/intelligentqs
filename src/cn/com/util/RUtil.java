package cn.com.util;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import redis.clients.jedis.Jedis;  

public class RUtil{

	public  Jedis con(){
		
		/*****1. 填写数据库相关信息(请查找数据库详情页)*****/
//		String databaseName = "KWeBrorVFKIPirXuMtfP"; 
//		String host = "redis.duapp.com"; //127.0.0.1
//		String portStr = "80"; //6379
//		int port = Integer.parseInt(portStr);
//		String username = "3dN4oydNzXvFL9NfDmdZ0KhP";//用户名(api key);
//		String password = "sGg3cjewYkf1Qivc2i4HYFG9Sw6Kz0WT";//密码(secret key)
//
//		/******2. 接着连接并选择数据库名为databaseName的服务器******/
//	  	Jedis jedis = new Jedis(host,port);
//	  	jedis.connect();
//	  	//auth：简单密码认证
//	  	jedis.auth(username + "-" + password + "-" + databaseName);
	  	/*至此连接已完全建立，就可对当前数据库进行相应的操作了*/
	  	/*3. 接下来就可以使用redis数据库语句进行数据库操作,详细操作方法请参考java-redis官方文档*/
	  	
	  	Jedis jedis = new Jedis("127.0.0.1",6379);
	  	return jedis;
	}
}