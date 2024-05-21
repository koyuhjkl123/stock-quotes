package Member;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User extends Admin implements Merbercontroll {

	
//	회원가입
	private String username; // 이름
	private String userid; // 아이디
	private String userpwd; // 비밀번호
	private String userpwds; // 비밀번호 확인
	private String useremail; // 이메일
	private String moblie; // 핸드폰번호
	private boolean approval_status = false; // 회원 승인 여부
	private int approval_key; // 회원가입 시 자동으로 생성되는 인증번호

	private String usersqlid;
	private String usersqlpwd;

//	당일 날짜
	LocalDateTime now = LocalDateTime.now();
//	SQL에 문법 : LocalDateTime을 java.sql.Timestamp로 변환
	Timestamp timestamp = Timestamp.valueOf(now);

	public boolean isApproval_status() {
		return approval_status;
	}

	public void setApproval_status(boolean approval_status) {
		this.approval_status = approval_status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public String getUsersqlid() {
		return usersqlid;
	}

	public void setUsersqlid(String usersqlid) {
		this.usersqlid = usersqlid;
	}

	public String getUsersqlpwd() {
		return usersqlpwd;
	}

	public void setUsersqlpwd(String usersqlpwd) {
		this.usersqlpwd = usersqlpwd;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserpwds() {
		return userpwds;
	}

	public void setUserpwds(String userpwds) {
		this.userpwds = userpwds;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public int getApproval_key() {
		return approval_key;
	}

	public void setApproval_key(int approval_key) {
		this.approval_key = approval_key;
	}


	@Override
	public void UserMembers() {
//		사용자가 바로 들어옴

	}

	@Override
	public void UserLogin() {

//		유저가 로그인 시 들어가는 메소드

		System.out.println("MERberShip의 오신것을 환영합니다.");
		System.out.println("1. 회원가입 2. 로그인 3. 비회원 로그인");

		int number_mership = sc.nextInt();

		if (number_mership == 1) {
			UserMemberShip();
		} else if (number_mership == 2) {

		} else if (number_mership == 3) {

		}

	}

	@Override
	public void UserMemberShip() {

		System.out.println("------- 회원가입 -------");
		System.out.println("이름을 입력하세요 : ");
		setUsername(sc.next());
		sc.nextLine();
		System.out.println("아이디를 입력하세요 : (10자 이내)");
		setUserid(sc.next());
		sc.nextLine(); // 개행문자
		System.out.println("비밀번호를 입력하세요 : (10자 이내)");
		setUserpwd(sc.next());
		sc.nextLine();

		while (true) {
			System.out.println("비밀번호확인 입력하세요 : ");
			setUserpwds(sc.next());
			if (getUserpwds().equals(getUserpwd())) {
				System.out.println("비밀번호와 일치합니다.");
			} else {
				System.out.println("비밀번호 일치하지 않습니다.");
				continue;
			}
			System.out.println("이메일을 입력하세요 : ");
			setUseremail(sc.next());
			sc.nextLine();

			System.out.println("이메일명@양식");
			System.out.println("1. naver 2. google 3. daum");
			int number_email_num = sc.nextInt();
			if (number_email_num == 1) {
				setUseremail(getUseremail() + "@naver.com");
			} else if (number_email_num == 2) {
				setUseremail(getUseremail() + "@google.com");
			} else if (number_email_num == 3) {
				setUseremail(getUseremail() + "@daum.com");
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}

			System.out.println("핸드폰 입력 : 예)01012345678) 하이픈(-)제외");
			setMoblie(sc.next());
			String PhoneNumber = FormatPhoneNumber(getMoblie());
			break;
		}
		String sql = "insert into usermerber (id, pw, email, mobile, reg_date, log_date, approval_status, approval_key) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			for (int i = 1; i <= 8; i++) {
				switch (i) {
				case 1:
					pstmt.setString(1, getUserid());
					break;
				case 2:
					pstmt.setString(2, getUserpwd());
					break;
				case 3:
					pstmt.setString(3, getUseremail());
					break;
				case 4:
					pstmt.setString(4, getMoblie());
					break;
				case 5:
					pstmt.setTimestamp(5, timestamp);
					break;
				case 6:
					pstmt.setTimestamp(6, timestamp);
					break;
				case 7:
					pstmt.setBoolean(7, approval_status);
					break;
				case 8:
					String randomKey = generateRandomKey();
					pstmt.setString(8, randomKey);
					break;
				}
			}

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("회원가입을 축하드립니다.");
				System.out.println("회원 이메일인증하기");
				Adminmore();
			} else {
				System.out.println("정보 업데이트가 실패 하였습니다.");
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("등록이 실패하였습니다.");
		}

	}
	public void AdminformatData() {

	}

	public void AdmintokenData() {

	}




}
