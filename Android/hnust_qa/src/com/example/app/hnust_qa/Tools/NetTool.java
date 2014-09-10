package com.example.app.hnust_qa.Tools;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by Administrator on 2014/7/31 0031.
 */
public class NetTool {
	
	public static String web = "http://10.1.11.234:8080/";

    public static String sessionId = null;

    public static String username = null;

    public static String userId = null;

    //检查网络
    public static boolean isWifiConnected(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return manager.isWifiEnabled();
    }

}
