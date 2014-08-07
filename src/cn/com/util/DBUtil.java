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
//		String Username=SaeUserInfo.getAccessKey();
//		String Password=SaeUserInfo.getSecretKey();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					//�ٶȿ��������ķ���������ʹ��
//					"jdbc:mysql://sqld.duapp.com:4050/XPLicCEZZoSPzufKXedM?useUnicode=true&characterEncoding=utf8", "3dN4oydNzXvFL9NfDmdZ0KhP", "sGg3cjewYkf1Qivc2i4HYFG9Sw6Kz0WT");
					//����SAE���ݿ�����
//					"jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_hnustqa?useUnicode=true&characterEncoding=utf8", Username,Password);
//			"jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_hnustqa?useUnicode=true&characterEncoding=utf8", "n1l0ol55n2","00j2ihl30lxx4i53mhy225xjl0x4kwh0yy3403y3");
					//�������ݿ⿪��ʹ��
			"jdbc:mysql://localhost:3306/hnust_qa?useUnicode=true&characterEncoding=utf8", "root","niuyichao");
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
