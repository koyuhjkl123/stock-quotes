import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TradedbTest {

	// getter와 setter
	private int id;
	private String countryid;
	private String name;
	private Long year;
	private Long income;
	private Long expense;
	private String imgoods;
	private String exgoods;

	// 데이터를 insert 할때 쓸 수 있는 생성자
	public ManageTrade(int id, String countryid, String name, long year, String imgoods, String exgoods, long income,
			long expense) {
		this.id = id;
		this.countryid = countryid;
		this.name = name;
		this.exgoods = exgoods;
		this.imgoods = imgoods;
		this.income = income;
		this.expense = expense;
		this.year = year;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public void setExpense(Long expense) {
		this.expense = expense;
	}

	public void setImgoods(String imgoods) {
		this.imgoods = imgoods;
	}

	public void setExgoods(String exgoods) {
		this.exgoods = exgoods;
	}

	public int getid() {
		return id;
	}

	public String getcountryid() {
		return countryid;
	}

	public String getname() {
		return name;
	}

	public Long getyear() {
		return year;
	}

	public Long getincome() {
		return income;
	}

	public Long getexpense() {
		return expense;
	}

	public String getimgoods() {
		return imgoods;
	}

	public String getexgoods() {
		return exgoods;
	}

	@Override
	public String toString() {
		return id + countryid + name + year + income + expense + imgoods + exgoods;

	}

	Connection conn;// 외부의 데이터를 불러오기

	ManageTrade() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver"; //
		String url = "jdbc:mysql://localhost:3306/tradedb"; // 어떤데이터베이스에 연결하고싶은지
		String userid = "root"; // 데이터베이스에 연결할때 사용자 이름
		String pwd = "1234";

		Class.forName(driver); // 없을수도 있어서 던져달라고 함!
		System.out.println("드라이버 연결 성공");
		System.out.println("드라이버 연결 준비.....");
		conn = DriverManager.getConnection(url, userid, pwd); // 데이터베이스에 연결을 만들기 위해 사용함
		System.out.println("빰빰1111111111 드라이버 연결 성공!!!");
	}

//	private void selectAll() throws Exception {
//		String sql = "select id, countryid, name, year, imgoods, exgoods, income, expense from tradedb";
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery(sql); // resultset은
//		while (rs.next()) { // rs.next (가 한줄을 가져옴) 그게 참일때까지
//			System.out.print("\t" + rs.getInt(1));
//			System.out.print("\t" + rs.getString(2));
//			System.out.print("\t" + rs.getString(3));
//			System.out.print("\t" + rs.getLong(4));
//			System.out.print("\t" + rs.getString(5));
//			System.out.print("\t" + rs.getString(6));
//			System.out.print("\t" + rs.getLong(7));
//			System.out.print("\t" + rs.getLong(8));
//
//			System.out.println();
//		}
//	}
	// 물음표는 1,2,3,4 와일드카드 무엇을 넣어야할지 모를때

	public void insertTradedb() throws Exception {
		String sql = "insert into tradetbl(id, countryid, name, year, imgoods, exgoods, income, expense) values (null,?,?,?,?,?,?,?)";
		Tradedb ct = new Tradedb();
		for (int i = 0; i <= 1; i++) {
			ct.admindb();
			if (getyear() == null || getincome() == null) {
				setYear((long) 0);
				setIncome((long) 0);
				setExpense((long) 0);
			}

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getcountryid());
			pstmt.setString(2, getname());
			pstmt.setLong(3, getyear());
			pstmt.setString(4, getimgoods());
			pstmt.setString(5, getexgoods());
			pstmt.setLong(6, getincome());
			pstmt.setLong(7, getexpense());

			System.out.println(getcountryid() + "메소드로 불러온 아이디 추가 성공");

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("메소드로 불러온 레코드 추가 성공");

			} else {
				System.out.println("메소드로 불러온 레코드 추가 실패!");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ManageTrade tt = new ManageTrade();
		tt.insertTradedb(); // 들어있는 데이터 전부 가져오기
	}

}
