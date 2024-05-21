package Member;

import java.sql.DriverManager;

public class Membercontroller extends User{
	
//	User, Admin 컨트롤하는 클래스
	
	public Membercontroller() {
		
//		데이터베이스 연결
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String sql_url_admin = "jdbc:mysql://localhost:3306/sqldb";
			
			setUsersqlid("root");
			setUsersqlpwd("1234");
			
			Class.forName(driver);
			con = DriverManager.getConnection(sql_url_admin, getUsersqlid(), getUsersqlpwd());
			System.out.println("로그인 성공하셧습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 실패하셨습니다.");
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		
		Membercontroller meber = new Membercontroller();
		
		meber.Adminmore();
	}
	
	
	

}
