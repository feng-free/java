package com.zsl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DataResult {

	private static Connection conn=null;
	private static String type = null;
	/*
	 * 连接数据库
	 * url:连接数据库的URL
	 * dataName:连接数据库的用户名
	 * password:连接数据的用户密码
	 * type:数据库类型
	 * */
	public DataResult(String URL,String dataName,String password){
		String driver = "com.mysql.jdbc.Driver";
		DataConnect d = null;
		if(conn == null){
			d = new DataConnect(URL,dataName,password,driver);
		}
		conn = d.getConnection();
	}
	/**
	 * @Author 张双亮
	 * @Description 通过MD5获取数据库数据
	 * @Param $param
	 * @return $return
	 **/
	
	public Set<String> resultMysql(Map<String,String> map){
		Set<String> set = new HashSet<>();
		PreparedStatement pst = null;
		ResultSet rs= null;
		try {
			for(String key : map.keySet()){
				String sql = "select md5 from "+ key + "where md5 in("+map.get(key)+")";
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()){
					set.add(rs.getString("MD5"));
				}
			}
		} catch (SQLException e) {
			System.out.println("查询失败");
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭查询失败");
				e.printStackTrace();
			}
		}
		return set;
	}
	
}
