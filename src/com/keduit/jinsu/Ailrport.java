package com.keduit.jinsu;

import java.sql.DriverManager;

public class Ailrport extends User {

	public Ailrport() {
		try { // 사용자(user)가 검색을 하기 위해 사용되는 생성자
			String driver = "com.mysql.cj.jdbc.Driver";
			String sql_url = "jdbc:mysql://localhost:3306/sqldb";
			setUserid("root");
			setPwd("1234");
			Class.forName(driver);
			con = DriverManager.getConnection(sql_url, getUserid(), getPwd());
			System.out.println("로그인 성공 하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	public static void main(String[] args) throws Exception {

		Ailrport a = new Ailrport(); // 생성자
//		a.Database();
		a.SqlSelect(); // 

	}
}