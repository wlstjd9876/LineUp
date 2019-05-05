package kr.teamapp.domain;


public class TeamAppDto {
	private int app_num;
	private String app_id;
	private int app_status;
	private String name;
	private String phone;
	private String age;
	private String gen;
	private int team_now_member;
	
	public int getTeam_now_member() {
		return team_now_member;
	}
	public void setTeam_now_member(int team_now_member) {
		this.team_now_member = team_now_member;
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
	public int getApp_status() {
		return app_status;
	}
	public void setApp_status(int app_status) {
		this.app_status = app_status;
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
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	
	
}