package com.webstudy.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: long
 * @date: 2021/3/24 11:58
 * @description:数据库连接工具类
 */
public class JDBCutil {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs;
	
	public JDBCutil() {}
	
	//Registration drive
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get database connection object
	 * @return Connection object
	 */
	public Connection getConnection(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","cxl362422");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * get database connection object
	 * @param request Tomcat请求对象
	 * @return Connection object
	 */
	public Connection getConnection(HttpServletRequest request){
		ServletContext application = request.getServletContext();
		Map map = (Map)application.getAttribute("key1");
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			conn = (Connection)it.next();
			if((boolean)map.get(conn)){
				map.put(conn,false);
				break;
			}
		}
		return conn;
	}

	/**
	 * get database statement object
	 * @param sql a sql command
	 * @return PreparedStatement object
	 */
	public PreparedStatement getStatement(String sql){
		Connection con = getConnection();
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	/**
	 * get database statement object
	 * @param sql a sql command
	 * @param request Tomcat请求对象
	 * @return PreparedStatement object
	 */
	public PreparedStatement getStatement(String sql, HttpServletRequest request){
		Connection con = getConnection(request);
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * release resource
	 */
	public void close() {
		try {
			if(ps != null)
				ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null)
				conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * release resource
	 */
	public void close(HttpServletRequest request) {
		try {
			if(ps != null)
				ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		ServletContext application = request.getServletContext();
		Map map = (Map)application.getAttribute("key1");
		map.put(conn,true);
	}

	/**
	 * release resource
	 * @param rs ResultSet
	 */
	public void close(ResultSet rs){
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	/**
	 * release resource
	 * @param rs ResultSet
	 * @param request 请求对象
	 */
	public void close(ResultSet rs,HttpServletRequest request){
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(request);
	}
}
