package com.darkmi.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
	private static final String url = "";
	private static final String username = "";
	private static final String pwd = "";

	public static void main(String[] args) {
		// 1. ע������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		// ����������ʹ�ã�����ر�
		Connection conn = null; //���ݿ�����
		Statement stmt = null; //���ݿ���ʽ
		ResultSet rs = null; //�����

		try {
			//2. ��ȡ���ݿ������
			conn = DriverManager.getConnection(
					"jdbc:mysql://67.20.120.174:3306/onezesm5_huoxingren?useUnicode=true&characterEncoding=GBK",
					"onezesm5_mixh", "sweet2dead");

			//3. ��ȡ���ʽ
			stmt = conn.createStatement();

			//4. ִ��SQL
			rs = stmt.executeQuery("select * from bbs_forum_activityapply");

			//5. ��ʵ��������������
			while (rs.next()) {
				//System.out.println("���=" + rs.getInt(1));
				//System.out.println("����=" + rs.getString("username"));
				//System.out.println("����=" + rs.getString("password"));
				//System.out.println("����=" + rs.getString("age"));
				System.out.println("---------------");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
