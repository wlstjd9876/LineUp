package kr.messenger.domain;

import java.util.Date;


public class MessengerDto {
	private int num;
	private String title;
	private String content;
	private Date reg_date;
	private String sender_id;
	private String receiver_id;
	private int check_m;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	public int getCheck_m() {
		return check_m;
	}
	public void setCheck_m(int check_m) {
		this.check_m = check_m;
	}
	
	
}