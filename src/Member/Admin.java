package Member;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Admin implements Merbercontroll {
//	관리자가 사용할 때
	
	
	Scanner sc = new Scanner(System.in);
	Connection con;
//	사용자 회원 가입 시 고유 키
	public String generateRandomKey() {
//		숫자 랜덤 생성
		SecureRandom secureRandom = new SecureRandom();
//		10자리의 인증번호를 생성하기 위해 10개의 스트링빌더를 생성한다.
		StringBuilder randomKeyBuilder = new StringBuilder(10);

		for (int i = 0; i < 10; i++) {
			randomKeyBuilder.append(secureRandom.nextInt(10));
		}

		return randomKeyBuilder.toString();
	}
	
//	사용자가 회원가입 승인처리
	public void Adminmore() {

		System.out.println("가입하신 이메일 입력 : ");
		String email = sc.next();
		String sql_email = "select * from usermerber where email = ? ";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql_email);

			pstmt.setString(1, email);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					System.out.println("입력하신 정보와 일치합니다.");
					System.out.println("입력하신 해당 메일에 인증번호를 전송합니다.");
					String email_pwd = "548312zxcv@";
			        String to = "koyuhjkl123@naver.com"; // 관리자 계정
			        String email_title = "이메일 인증";
//			        6자리 숫자가 랜덤으로 발송됨
			        String body = "인증 코드: " + generateVerificationCode();
			        
			        String is_key = body.substring(7); // 해당 발송된 인증 메일
			        System.out.println("생성된 인증 코드: " + is_key);
			        // 발신 이메일 계정 설정
			        String from = "koyu12315@gmail.com"; // 관리자 이메일 정보
			        String password = "qoli ivvo sasc ofae"; // 관리자 이메일 비밀번호

			        // SMTP 서버 설정
			        String host = "smtp.gmail.com";
			        String port = "587";

			        // 이메일 전송
			        SendEmail(to, email_title, body, from, password, host, port);
			        
			        System.out.println("해당 메일로 받으신 인증번호를 입력하세요 : ");
			        String user_is_key = sc.next();
			        
			        if(user_is_key.equals(is_key)) {
			        	AdminIsUpdate(email);
			        }else {
			        	System.out.println("고객님의 인증메일이 실패하였습니다.");
			        }
				} else {
					System.out.println("입력하신 정보와 일치하지 않습니다.");
				}
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void AdminIsUpdate(String email) {
		
		String sql_isupdate = "update usermerber set approval_status = ? where email = ? ";
		
		
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql_isupdate);
			
			pstmt.setBoolean(1, true);
			pstmt.setString(2, email);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("고객님의 인증 메일이 완료되었습니다.");
			}else {
				System.out.println("고객님의 인증 메일이 실패하였습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void SendEmail(String to, String email_title, String body, String from, String password, String host, String port) {
//		1. to : 사용자 인증번호 받은 메일,  email_title : 이메일 제목
//		2. body : 이메일 본문 내용,  from : 관리자 메일 정보,  password : 관리자 메일 비밀번호
//		3. host : 이메일 서버 호스트 주소 ,  port : 이메일 서버 포트 번호
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
//        qoli ivvo sasc ofae // 2차 앱 비밀번호
        
//       session 객체 생성: 이메일 전송에 필요한 세션을 설정
//        Authenticator를 이용한 인증: Authenticator 클래스를 상속받아 발신자 계정의 아이디와 비밀번호를 제공하여 이메일 서버에 인증
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
//           해당 클래스의 메소드는 아이디와 비밀번호를 저장하는데 사용되고 이메일 발송 시 이 정보를 사용하여 
//            이메일 서버에 로그인하고 발신자 이메일 주소와 관련된 계정으로 인증을 수행
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
//        	MimeMessage : 메세지의 구조와 내용을 설정
//        	수신자, 제목, 본문 설정: 수신자 주소, 이메일 제목, 본문 내용을 설정
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // 관리자 이메일 정보
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 사용자 이메일
            message.setSubject(email_title); // 이메일 제목
            message.setText(body); // 이메일 본문

            Transport.send(message); // 설정된 메세지를 전송함
            System.out.println("이메일이 성공적으로 전송되었습니다.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
//	이메일 인증코드 키
	public String generateVerificationCode() {
        // 여기에서 랜덤한 인증 코드를 생성하는 코드를 추가할 수 있습니다.
    	SecureRandom secureRandom = new SecureRandom();
//		10자리의 인증번호를 생성하기 위해 10개의 스트링빌더를 생성한다.
		StringBuilder randomKeyBuilder = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {
			randomKeyBuilder.append(secureRandom.nextInt(10));
		}

		return randomKeyBuilder.toString();
    }
	
	
//	사용자 핸드폰번호 양식
	@Override
	public String FormatPhoneNumber(String phoneNumber) {
		// 숫자 이외의 문자 모두 제거
		String cleanedNumber = phoneNumber.replaceAll("[^0-9]", "");

		// 형식에 맞게 변환 (010-1234-5678)
		if (cleanedNumber.length() == 11) {
			return cleanedNumber.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
		} else {
			// 형식에 맞지 않는 경우 그대로 반환
			return cleanedNumber;
		}
	}
}
