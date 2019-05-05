package kr.team.hire.domain;

import kr.location.domain.LocationDto;

public class TeamDto {
	private int team_num;
	private String team_id;
	private int team_loc_num;
	private int team_member;
	private String team_date;
	private String team_end_date;
	private String team_age;
	private int team_money;
	private String team_etc;
	private LocationDto loc;
	private String date[];
	private String end_date[];
	private int team_now_member;

	public int getTeam_now_member() {
		return team_now_member;
	}
	public void setTeam_now_member(int team_now_member) {
		this.team_now_member = team_now_member;
	}



	public String[] getDate() {
		return date;
	}
	public void setDate(String[] date) {
		this.date = date;
	}
	public String[] getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String[] end_date) {
		this.end_date = end_date;
	}
	public LocationDto getLoc() {
		return loc;
	}
	public void setLoc(LocationDto loc) {
		this.loc = loc;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public int getTeam_loc_num() {
		return team_loc_num;
	}
	public void setTeam_loc_num(int team_loc_num) {
		this.team_loc_num = team_loc_num;
	}
	public int getTeam_member() {
		return team_member;
	}
	public void setTeam_member(int team_member) {
		this.team_member = team_member;
	}
	public String getTeam_date() {
		return team_date;
	}
	public void setTeam_date(String team_date) {
		this.team_date = team_date;
	}
	public String getTeam_end_date() {
		return team_end_date;
	}
	public void setTeam_end_date(String team_end_date) {
		this.team_end_date = team_end_date;
	}
	public String getTeam_age() {
		return team_age;
	}
	public void setTeam_age(String team_age) {
		this.team_age = team_age;
	}
	public int getTeam_money() {
		return team_money;
	}
	public void setTeam_money(int team_money) {
		this.team_money = team_money;
	}
	public String getTeam_etc() {
		return team_etc;
	}
	public void setTeam_etc(String team_etc) {
		this.team_etc = team_etc;
	}


}