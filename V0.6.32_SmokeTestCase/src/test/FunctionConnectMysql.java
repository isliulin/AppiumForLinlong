package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class FunctionConnectMysql {
	private static String Username = "testlink";
	private static String Password = "testlink";
	private static String URL = "192.168.4.124:3306";

	public Connection ConnectMysql() throws Exception {
		String url = "jdbc:mysql://" + URL + "/testlink?" + "user=" + Username
				+ "&password=" + Password
				+ "&useUnicode=true&characterEncoding=UTF8";

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url);

		System.out.println(url);
		System.out.println("链接mysql成功");

		return conn;

	}

}
