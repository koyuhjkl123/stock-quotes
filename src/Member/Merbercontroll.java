package Member;

public interface Merbercontroll {
	
//	옷 쇼핑몰 데이터
	
//	사용자 관리 메뉴단 메서드
	
//	사용자 로그인 메소드
	default public void UserLogin() {};
//	사용자 회원가입 메소드
	default public void UserMemberShip() {};
//	사용자 컨트롤? 메소드
	default public void UserMembers() {};

	
//	관리자메뉴 메서드
	
	default public void Admin() {};
//	관리자 사용자가 회원가입 시 승인처리
	default public void Adminmore() {};
	
	default public void AdminformatData() {};
	
	default public void AdmintokenData() {};
	
//	관리자 사용승인 상태를 변환 주는 메서드
	default public void AdminIsUpdate() {};
	
//	이메일 인증번호를 보내기 위한 6자리 랜덤 숫자 인증코드 생성
	default public String generateVerificationCode() {return "";};
//	사용자가 회원 가입 시 받은 고유 회원 키
	default public String generateRandomKey() {return "";};
//	사용자 핸드폰번호 양식 구현 메서드
	default public String FormatPhoneNumber(String phoneNumber) { return "";};
//	사용자 이메일 인증
	default public void SendEmail(String to, String email_title, String body, 
			String from, String password, String host, String port) {};
	
}


//id (varchar(20) primary key):
//
//의미: 회원의 고유 식별자.
//특징: 최대 20자의 문자열, 기본 키(primary key)로 설정되어 있어 각 행을 고유하게 식별합니다.
//pw (varchar(20) not null):
//
//의미: 회원의 비밀번호.
//특징: 최대 20자의 문자열, null 값은 허용되지 않습니다(not null).
//email (varchar(40) unique):
//
//의미: 회원의 이메일 주소.
//특징: 최대 40자의 문자열, 중복된 값이 허용되지 않도록 unique 제약이 설정되어 있습니다.
//reg_date (datetime not null):
//
//의미: 회원 가입일.
//특징: 날짜와 시간을 나타내는 datetime 데이터 유형, null 값은 허용되지 않습니다(not null).
//log_date (datetime not null):
//
//의미: 회원의 마지막 로그인 일시.
//특징: 날짜와 시간을 나타내는 datetime 데이터 유형, null 값은 허용되지 않습니다(not null).
//approval_status (varchar(10) not null):
//
//의미: 회원 가입 승인 상태를 나타내는 열.
//특징: 최대 10자의 문자열, null 값은 허용되지 않습니다(not null).
//approval_key (varchar(10)):
//
//의미: 회원 가입 승인을 위한 특별한 키 또는 코드.
//특징: 최대 10자의 문자열, null 값이 기본적으로 허용됩니다.


//create table UserMerber(
//		  id varchar(20) primary key,
//		  pw varchar(20) not null, 
//		  email varchar(40) unique,
//		  mobile varchar(40) not null,
//		  reg_date datetime not null,
//		  log_date datetime not null,
//		  approval_status varchar(10) not null,
//		  approval_key varchar(10)
//		);