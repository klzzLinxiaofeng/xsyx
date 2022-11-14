package platform.education.commonResource.serivce.unit;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 2017-08-02
 * 
 * JDBC操作工具类
 * @author 【pantq】
 *
 */
public class JDBCUitl {

	private final static String DBFILE = "/jdbc.properties";
	
	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection= null;
		try {
			InputStream inputStream = JDBCUitl.class.getResourceAsStream(DBFILE);  
			Properties prop = new Properties();  
			prop.load(inputStream);  
			String USERNAME = prop.getProperty("jdbc.username");  
			String PASSWORD = prop.getProperty("jdbc.password");  
			String DRIVER= prop.getProperty("jdbc.driverClassName");  
			String URL = prop.getProperty("jdbc.url");
			
			Class.forName(DRIVER); // 注册驱动  
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	/**
	 * 开始事务
	 * @param connection
	 */
	public static void beginTx(Connection connection,Boolean flag) {
		try {
			
			if(connection != null) {
				connection.setAutoCommit(flag);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 事务提交
	 * @param connection
	 */
	public static void commit(Connection connection) {
		try {
			
			if(connection != null) {
				connection.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 事务回滚
	 * @param connection
	 */
	public static void rollback(Connection connection) {
		try {
			
			if(connection != null) {
				
				connection.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放资源
	 * @param connection
	 * @param pstmt
	 */
	public static void free(Connection connection,PreparedStatement pstmt) {
		
		try {
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(connection != null) {
				connection.close();
			}
		}catch (Exception e) {
		}
	}
	
	
	
}
