package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UnitSql {
	Connection conn;
	String sql;
	String url;
	PreparedStatement ps = null; // 楼楼惟庐PreparedStatement 鈭傗�樑撁�
	ResultSet rs = null;
	int oldid;

	public void InserData(int build_id, int tester_id, String status,
			int testplan_id, int tcversion_id) throws Exception {
		FunctionConnectMysql Connect = new FunctionConnectMysql();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 鈥γ嬅封垰禄鈥欌垎鈦勨垙脪聽惟
		System.out.println(df.format(new Date()));
		Calendar.getInstance().getTimeInMillis();
		conn = Connect.ConnectMysql();
		oldid = SelectData("executions");
		System.out.println(oldid);
		sql = "INSERT INTO  executions (id , build_id , tester_id ,execution_ts, status,testplan_id , tcversion_id , tcversion_number, platform_id , execution_type  ) VALUES (?,?,?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, oldid + 1);
		ps.setInt(2, build_id);
		ps.setInt(3, tester_id);
		ps.setString(4, df.format(new Date()));
		ps.setString(5, status);
		ps.setInt(6, testplan_id);
		ps.setInt(7, tcversion_id);
		ps.setInt(8, 1);
		ps.setInt(9, 0);
		ps.setInt(10, 1);
		ps.executeUpdate();
		System.out.println(sql);
	}

	public void UpdateData(String table, String Rawdata, String NewData,
			String condition, String Value) {
		sql = ("update " + table + " set" + Rawdata + "=" + NewData + " where"
				+ condition + "=" + Value);
		System.out.println(sql);

	}

	public int SelectData(String table) throws Exception {
		FunctionConnectMysql Connect = new FunctionConnectMysql();
		conn = Connect.ConnectMysql();
		sql = "select id from executions order by id desc limit 0,1";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println("id" + "\t");

		while (rs.next()) {

			System.out.println(rs.getInt(1) + "\t");
			return rs.getInt(1);
		}

		return (Integer) null;
	}
}
