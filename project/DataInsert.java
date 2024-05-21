package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class DataInsert extends Tradedb {

	public DataInsert(int id, String countryid, String name, long year, String imgoods, String exgoods, long income,
			long expense) {
		super(id, countryid, name, year, imgoods, exgoods, income, expense);
		// TODO Auto-generated constructor stub
	}

	static String driver = "com.mysql.cj.jdbc.Driver"; //
	static String url = "jdbc:mysql://localhost:3306/tradedb"; // 어떤데이터베이스에 연결하고싶은지
	static String userid = "root"; // 데이터베이스에 연결할때 사용자 이름
	static String pwd = "1234";

	public static void insertData(List<_CountriesTrade> pl) throws Exception {
		Connection conn = DriverManager.getConnection(url, userid, pwd);
		for (_CountriesTrade trade : pl) {
			String sql = "insert into tradetbl(id, countryid, name, year, imgoods, exgoods, income, expense) values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql){
				pstmt.setString(1, _CountriesTrade.get);
			}
			
			
			
			
		
		}
	}

	public static void main(String[] args) {

	}
}
