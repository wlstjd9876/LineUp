package kr.soloapp.domain;


public class SoloAppDto {
	private int app_num;
	private String app_id;
	private int app_member;
	private String name;
	private String phone;
	private String age;
	private String gen;
	
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getApp_num() {
		return app_num;
	}
	public void setApp_num(int app_num) {
		this.app_num = app_num;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public int getApp_member() {
		return app_member;
	}
	public void setApp_member(int app_member) {
		this.app_member = app_member;
	}
	
	
}