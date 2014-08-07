package cn.com.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.renren.api.client.RenrenApiConfig;


public class ApiInitListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		//Nothing to do
	}

	public void contextInitialized(ServletContextEvent arg0) {
		RenrenApiConfig.renrenApiKey = AppConfig.API_KEY;
		RenrenApiConfig.renrenApiSecret = AppConfig.APP_SECRET;
	}
}