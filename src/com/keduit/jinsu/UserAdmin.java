package com.keduit.jinsu;

import java.sql.ResultSet;

public interface UserAdmin {
	
//	사용자 메소드
//	1. 사용자, 관리자 메뉴 메서드
	default public void SqlSelect() {};
//	2. 사용자에게 종목명을 검색할 수 있게 하는 메서드
	default public void UserItmsNmsSelect(String itmsNms_name) {};
//	3. 사용자에게 특정 날짜를 검색할 수 있게 하는 메서드
	default public void UserSelectDate(String date) {};
//	4. 사용자에게 거래량 순위(오름차순, 내림차순)별로 10위, 50위, 100위 내에 검색 가능
	default public void UserSelectMrkttotamt(int order, int rank) {};
//	5. 사용자에게 시가 총액 순위(오름차순, 내림차순)별로 10위, 50위, 100위 내에 검색 가능
	default public void UserSelectTrqu(int number, int rank) {};
//	6. 거래량 및 시가총액 구분
	default public void SelectUser(ResultSet rs) {};
	
//	관리자 메소드
//	1. 관리자 SQL 데이터 추가하기
	default public void AdminInsert() {};
//	2. 관리자 SQL Udate 수정하기
	default public void AdminUdate() {};
//	3. 관리자 SQL Delete 삭제하기
	default public void AdminDelete() {};
//	4. 관리자 SQL Select 검색하기
	default public void AdminSelect() {};
//	5. 관리자 API 데이터를 가져오기 위한 메서드
	default public void Database() {};
//	6. 관리자 API 데이터를 가져올 떄 테이블 생성이 중복 여부를 검사하기위한 메서드
	default public boolean isTableExists() {return false;};
//	7. 관리자 API 데이터값을 통해 SQL에 테이블 생성 및 데이터 넣기 위한 메서드
	default public void AdminDatabase() {};
//	8. 관리자 메일 인증 발송
	default public void SendEmail(String user_email, String email_title, String email_body, 
			String email_admin, String admin_password, String email_server_host, String email_server_port) {};
//	9. 관리자 이메일 인증코드 키
	default public String GenerateVerificationCode() {return "";};
//	10. 관리자 이메일 일치 검사
	default public void AdminEmail(String email) {};
}
