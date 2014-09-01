package cn.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ�DB
 * @author Xianxiaofei
 *
 */
public class DBUtil {
	
	
	//������ӷ���
	public Connection getConn() {
		Connection conn = null;
		
		/*****��д���ݿ������Ϣ ע���е���ϢΪ�ٶȿ��������ķ�����ʹ��*****/
//		String databaseName = "XPLicCEZZoSPzufKXedM"; //XPLicCEZZoSPzufKXedM
//		String host = "sqld.duapp.com"; //sqld.duapp.com
//		String portStr = "4050"; //4050
//		int port = Integer.parseInt(portStr);
//		String username = "3dN4oydNzXvFL9NfDmdZ0KhP";//�û���:  3dN4oydNzXvFL9NfDmdZ0KhP
//		String password = "sGg3cjewYkf1Qivc2i4HYFG9Sw6Kz0WT";//����: sGg3cjewYkf1Qivc2i4HYFG9Sw6Kz0WT
		
//		//����ʹ��
		String databaseName = "hnust_qa"; //XPLicCEZZoSPzufKXedM
		String host = "localhost"; //sqld.duapp.com
		String portStr = "3306"; //4050
		int port = Integer.parseInt(portStr);
		String username = "root";//�û���:  3dN4oydNzXvFL9NfDmdZ0KhP
		String password = "niuyichao";//����: sGg3cjewYkf1Qivc2i4HYFG9Sw6Kz0WT
		
		String conDriver = "jdbc:mysql://"+host+":"+portStr+"/"+databaseName+"?useUnicode=true&characterEncoding=utf8";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(conDriver, username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return conn;
	}
	
	//�ͷ���Դ
	public void free(ResultSet rs,Statement pstm,Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void free(Statement pstm,Connection conn) {
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
