# 금융위원회_주식시세정보
주식 시세정보 2023(12월)에 대한 시장(코스닥) 시세정보 파악
공공데이터를 API를 활용한 CRUD 구현을 하고자 만든 개인 프로젝트입니다.


# 목차
* 개요
* 프로젝트 설계
* 사용자 및 관리자 기능 설명
* 프로젝트 후기


# 개요
* 프로젝트 이름 : 주식시세정보
* 프로젝트 개발 기간 : 2024.01 ~ 2024.01(12일)
* 프로젝트 구성 인원 : 1명(개인 프로젝트)
* 프로젝트 개발 환경
  * 언어 : JAVA
  * DB : MySQL

<br>

# 프로젝트 설계

## 프로젝트 일정
<details>
    <summary>WBS</summary>

![image](https://github.com/koyuhjkl123/stock-quotes/assets/94844952/8c3ebe57-b575-4ee1-aeea-5e5959326be0)

</details>

## 인터페이스 메소드
<details>
    <summary>코드 보기</summary>


![image](https://github.com/koyuhjkl123/stock-quotes/assets/94844952/969041e2-ca6f-4dd7-b769-dc1a2bb7740f)

</details>

# 사용자 및 관리자 기능 설명

## 사용자 기능 구현

* 비회원 이용자 이메일 인증
<details>
    <summary>코드 보기</summary>

```java
@Override
	public void SqlSelect() {
		System.out.println("---------- 주식시세정보 ----------");
		System.out.print("1. 비 회원 이용자  |  2. 관리자");
		
		int user = sc.nextInt();
		if (user == 1) {
			System.out.println("1회용으로 검색하실 이메일이 필요합니다.");
			System.out.println("인증하실 이메일 명 :");
			String email = sc.next();
			AdminEmail(email); // 해당 메소드의 매개변수 사용자 이메일

// 이메일 인증 시 아래와 같이 Admin 클래스에 있는 메소드들이 실행됩니다.
@Override
	public void AdminEmail(String email) {

		System.out.println("입력하신 해당 메일에 인증번호를 전송합니다.");
		String user_email = email; // 사용자 이메일

		String email_title = "이메일 인증"; // 사용자가 받을 이메일 제목
//			        6자리 숫자가 랜덤으로 발송됨
		String user_email_body = "인증 코드: " + GenerateVerificationCode();

		String is_key = user_email_body.substring(7); // 해당 발송된 인증 메일
		System.out.println("생성된 인증 코드: " + is_key);
		// 발신 이메일 계정 설정
		String admin_email = "koyu12315@gmail.com"; // 관리자 이메일 정보
		String admin_email_pwd = "qoli ivvo sasc ofae"; // 관리자 이메일 비밀번호

		// SMTP 서버 설정
		String email_server_host = "smtp.gmail.com";
		String email_server_port = "587";

		// 이메일 전송
		SendEmail(user_email, email_title, user_email_body, admin_email, admin_email_pwd, email_server_host, email_server_port);

		System.out.println("해당 메일로 받으신 인증번호를 입력하세요 : ");
		String user_is_key = sc.next();

		if (user_is_key.equals(is_key)) {
			System.out.println("고객님의 인증메일이 성공하였습니다.");
		} else {
			System.out.println("고객님의 인증메일이 실패하였습니다.");
		}
	}

	@Override
//	관리자 메일로 사용자에게 인증메일 발송
	public void SendEmail(String user_email, String email_title, String email_body, String admin_email,
			String admin_email_pwd, String email_server_host, String email_server_port) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); // SMTP 인증을 사용할지 여부를 나타냅니다. "true"로 설정하면 SMTP 서버에 대한 인증
//		SMTP 인증은 이메일을 보내려는 사용자가 자신의 계정 정보로 서버에 로그인하여 승인되어야만 메일을 발송할 수 있도록 하는 보안 기능
		properties.put("mail.smtp.starttls.enable", "true"); // 사용하여 암호화된 통신을 활성화할지 여부를 나타냅니다. "true"로 설정하면 암호화된 통신이 활성화
//		이메일 클라이언트는 SMTP 서버와의 통신 시에 TLS를 사용하여 데이터를 암호화합니다. T
//		LS를 사용하면 데이터가 전송 중에 안전하게 보호되므로 중요한 정보가 포함된 이메일이 누출되거나 조작되지 않도록 보안성을 향상
		properties.put("mail.smtp.host", email_server_host);
		properties.put("mail.smtp.port", email_server_port);
//	        qoli ivvo sasc ofae // 2차 앱 비밀번호

//	       session 객체 생성: 이메일 전송에 필요한 세션을 설정
//	        Authenticator를 이용한 인증: Authenticator 클래스를 상속받아 발신자 계정의 아이디와 비밀번호를 제공하여 이메일 서버에 인증
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
//	           해당 클래스의 메소드는 아이디와 비밀번호를 저장하는데 사용되고 이메일 발송 시 이 정보를 사용하여 
//	            이메일 서버에 로그인하고 발신자 이메일 주소와 관련된 계정으로 인증을 수행
			protected PasswordAuthentication getPasswordAuthentication() {
//				JavaMail API를 사용하여 이메일을 보낼 때, 발신자 이메일 주소와 관련된 계정으로 SMTP 서버에 로그인하여 발신자의 신원을 확인하고 이메일을 보낼 수 있도록 하는 역할
				return new PasswordAuthentication(admin_email, admin_email_pwd);
				}
		});
		try {
//	        	MimeMessage : 메세지의 구조와 내용을 설정
//	        	수신자, 제목, 본문 설정: 수신자 주소, 이메일 제목, 본문 내용을 설정
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(admin_email)); // 관리자 이메일 정보
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user_email)); // 사용자 이메일
			message.setSubject(email_title); // 이메일 제목
			message.setText(email_body); // 이메일 본문

			Transport.send(message); // 설정된 메세지를 전송함
			System.out.println("이메일이 성공적으로 전송되었습니다.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
//	사용자에게 이메일 발송 시 인증코드 6자리 생성하는 메서드
	public String GenerateVerificationCode() {

//		SecureRandom : 클래스는 자바에서 암호학적으로 안전한 난수를 생성하기 위한 클래스
//		다양한 알고리즘을 사용하여 난수를 생성할 수 있으며, 예측 불가능하고 안전한 특성을 갖도록 구현
//		보안 관련한 인증코드 발송 시 해당 클래스를 사용하는 것이 좋다
//		Random 클래스와는 달리 보다 안전한 난수를 생성
//		일반적인 Random 클래스는 알고리즘이 예측 가능하고, 암호학적으로 안전하지 않은 난수를 생성할 수 있기 때문에 보안적인 요구사항이 있는 상황에서는 사용 x
		SecureRandom random = new SecureRandom();
//		10자리의 인증번호를 생성하기 위해 10개의 스트링빌더를 생성한다.
		StringBuilder randomKey = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {
//			0 ~ 9까지 랜덤으로 6번 반복해서 randomKeyBuilder에 넣는다.
			randomKey.append(random.nextInt(10));
		}

		return randomKey.toString();
	}
```
</details>


* 사용자 검색(1번 ~ 4번)
종목명 검색, 특정 날짜 검색, 시가총액 검색, 거래량 검색 등을 할 수 있습니다.

<details>
    <summary>코드 보기</summary>

```
while (true) { // 5번 선택 시 끝남
				System.out.println("----------- 주식시세정보 (-검색-) --------");
				System.out.println("1. 종목명 검색  |  2. 특정 날짜 검색 | 3. 시가총액 검색  | 4. 거래량 검색  | 5. 검색 종료");

				int user_select = sc.nextInt();
//				sc.nextLine(); // 개행 문자 소비
				if (user_select == 1) {
					System.out.println("---------- 종목명(-검색-)을 선택하셨습니다! --------");
					System.out.println("종목명을 입력하세요 : ");
					String itmsnm_name = sc.next();
					UserItmsNmsSelect(itmsnm_name);
				} else if (user_select == 2) {
					System.out.println("---------- " + "특정 날짜 검색 " + "(-전체검색-) ----------");
					System.out.println("날짜 : 2023년 12월 4일 ~ 2023년 12월 28일");
					System.out.println("날짜 입력 양식 예시 : 20231212");
					System.out.println("날짜 입력 시 보여주는 종목 수 : 100개");
					String date = sc.next();
					UserSelectDate(date);
				} else if (user_select == 3) {
					System.out.println("---------- " + "시가총액 순위 " + "(-시가총액 검색-) ----------");
					System.out.println("----- 원하시는 시가총액 정보를 입력하세요 -----");
					System.out.println("1. 시가총액 높은 순  2. 시가총액 낮은 순");
					int user_mt = sc.nextInt();
					sc.nextLine(); // 개행 문자
					System.out.println("1. 10위내  2. 50위내  3. 100위내");
					int user_mt_rank = sc.nextInt();
					UserSelectMrkttotamt(user_mt, user_mt_rank); // 시가총액 메서드

				} else if (user_select == 4) {
					System.out.println("---------- 거래량 검색 (--검색--) ----------");
					System.out.println("--------- 원하시는 거래량 정보를 입력하세요");
					System.out.println("1. 거래량 높은 순  2. 거래량 낮은 순");
					int user_trqu = sc.nextInt();
					sc.nextLine(); // 개행 문자
					System.out.println("1. 10위내  2. 50위내  3. 100위내");
					int user_trqu_rank = sc.nextInt();
					UserSelectTrqu(user_trqu, user_trqu_rank); // 거래량 메서드
				} else if (user_select == 5) {
					System.out.println("해당 검색을 종료하시겠습니까?");
					System.out.println("1. 네  |  2. 아니요");
					int user_select_end = sc.nextInt();

					if (user_select_end == 1) {
						System.out.println("검색창을 종료하였습니다.");
						break;
					} else if (user_select_end == 2) {
						System.out.println("처음 선택지로 넘어갑니다.");
						continue;
					}
				}
```
</details>
