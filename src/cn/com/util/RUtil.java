package cn.com.util;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import redis.clients.jedis.Jedis;  

public class RUtil{

	public  Jedis con(){
		
		/*****1. ��д���ݿ������Ϣ(��������ݿ�����ҳ)*****/
//		String databaseName = "KWeBrorVFKIPirXuMtfP"; 
//		String host = "redis.duapp.com"; //127.0.0.1
//		String portStr = "80"; //6379
//		int port = Integer.parseInt(portStr);
//		String username = "3dN4oydNzXvFL9NfDmdZ0KhP";//�û���(api key);
//		String password = "sGg3cjewYkf1Qivc2i4HYFG9Sw6Kz0WT";//����(secret key)
//
//		/******2. �������Ӳ�ѡ�����ݿ���ΪdatabaseName�ķ�����******/
//	  	Jedis jedis = new Jedis(host,port);
//	  	jedis.connect();
//	  	//auth����������֤
//	  	jedis.auth(username + "-" + password + "-" + databaseName);
	  	/*������������ȫ�������ͿɶԵ�ǰ���ݿ������Ӧ�Ĳ�����*/
	  	/*3. �������Ϳ���ʹ��redis���ݿ����������ݿ����,��ϸ����������ο�java-redis�ٷ��ĵ�*/
	  	
	  	Jedis jedis = new Jedis("127.0.0.1",6379);
	  	return jedis;
	}
}