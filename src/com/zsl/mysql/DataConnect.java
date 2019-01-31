package com.zsl.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
	private  String URL;
	private String USER;
	private String PASSWORD;
	private String Driver;
	private Connection conn=null;
	/*
	 * 连接数据库
	 * url:连接数据库的URL
	 * dataName:连接数据库的用户名
	 * password:连接数据的用户密码
	 * driver:数据库路径
	 * */
	DataConnect(String URL,String dataName,String password,String driver){
		this.URL  = URL;
		this.USER = dataName;
		this.PASSWORD = password;
		this.Driver = driver;
	}
	//将获得的数据库与java的链接返回（返回的类型为Connection）
	Connection getConnection(){
		try{
			//1,加载驱动程序
			Class.forName(Driver);
			//2.获得数据库链接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		}catch(Exception e){
			e.printStackTrace();
		}
		    return conn;
	}
}
