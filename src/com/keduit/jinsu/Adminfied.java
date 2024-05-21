package com.keduit.jinsu;

import java.sql.Connection;
import java.util.Scanner;

public class Adminfied {
	
	// 관리자 메뉴얼
		Scanner sc = new Scanner(System.in);
		Connection con;

		String[] sql_insert = { "종목명", "날짜", "종가", "대비", "등락률", "시가", "고가", "저가", "거래량", "시가총액" };
		private String itmsNms; // 종목명
		private String mrktTotAmt; // 시가 총액
		private String basDt; // 기준 일자
		private String fltRt; // 등락률
		private String trqu; // 거래량
		private String vs; // 대비
		private String clpr; // 종가
		private String mkp; // 시가
		private String hipr; // 고가
		private String lopr; // 저가

		private String userid; // 유저 아이디
		private String pwd; // 유저 비밀번호

		public String getVs() {
			return vs;
		}

		public void setVs(String vs) {
			this.vs = vs;
		}

		public String getClpr() {
			return clpr;
		}

		public void setClpr(String clpr) {
			this.clpr = clpr;
		}

		public String getMkp() {
			return mkp;
		}

		public void setMkp(String mkp) {
			this.mkp = mkp;
		}

		public String getHipr() {
			return hipr;
		}

		public void setHipr(String hipr) {
			this.hipr = hipr;
		}

		public String getLopr() {
			return lopr;
		}

		public void setLopr(String lopr) {
			this.lopr = lopr;
		}

		public String getFltRt() {
			return fltRt;
		}

		public String getTrqu() {
			return trqu;
		}

		public String getEndFltRt() {
			return fltRt;
		}

		public void setFltRt(String fltRt) {
			this.fltRt = fltRt;
		}

		public String getEndTrqu() {
			return trqu;
		}

		public void setTrqu(String trqu) {
			this.trqu = trqu;
		}

		public String getItmsNms() {
			return itmsNms;
		}

		public void setItmsNms(String itmsNms) {
			this.itmsNms = itmsNms;
		}

		public String getMrktTotAmt() {
			return mrktTotAmt;
		}

		public void setMrktTotAmt(String mrktTotAmt) {
			this.mrktTotAmt = mrktTotAmt;
		}

		public String getBasDt() {
			return basDt;
		}

		public void setBasDt(String basDt) {
			this.basDt = basDt;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		


}
