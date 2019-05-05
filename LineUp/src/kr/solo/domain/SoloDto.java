package kr.solo.domain;

import kr.location.domain.LocationDto;

public class SoloDto {
	private int solo_now_member;
	private int solo_num;
	private int solo_min_member;
	private int solo_member;
	private String solo_end_date;
	private String solo_date;
	private int solo_loc_num;
	private String solo_id;
	private int solo_money;
	private String solo_age;
	private String solo_gen;
	private LocationDto loc;
	private String date[];
	private String end_date[];
	private boolean check;
	
	
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
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
	public int getSolo_now_member() {
		return solo_now_member;
	}
	public void setSolo_now_member(int solo_now_member) {
		this.solo_now_member = solo_now_member;
	}
	public int getSolo_num() {
		return solo_num;
	}
	public void setSolo_num(int solo_num) {
		this.solo_num = solo_num;
	}
	public int getSolo_min_member() {
		return solo_min_member;
	}
	public void setSolo_min_member(int solo_min_member) {
		this.solo_min_member = solo_min_member;
	}
	public int getSolo_member() {
		return solo_member;
	}
	public void setSolo_member(int solo_member) {
		this.solo_member = solo_member;
	}
	public String getSolo_end_date() {
		return solo_end_date;
	}
	public void setSolo_end_date(String solo_end_date) {
		this.solo_end_date = solo_end_date;
	}
	public String getSolo_date() {
		return solo_date;
	}
	public void setSolo_date(String solo_date) {
		this.solo_date = solo_date;
	}
	public int getSolo_loc_num() {
		return solo_loc_num;
	}
	public void setSolo_loc_num(int solo_loc_num) {
		this.solo_loc_num = solo_loc_num;
	}
	public String getSolo_id() {
		return solo_id;
	}
	public void setSolo_id(String solo_id) {
		this.solo_id = solo_id;
	}
	public int getSolo_money() {
		return solo_money;
	}
	public void setSolo_money(int solo_money) {
		this.solo_money = solo_money;
	}
	public String getSolo_age() {
		return solo_age;
	}
	public void setSolo_age(String solo_age) {
		this.solo_age = solo_age;
	}
	public String getSolo_gen() {
		return solo_gen;
	}
	public void setSolo_gen(String solo_gen) {
		this.solo_gen = solo_gen;
	}
	public LocationDto getLoc() {
		return loc;
	}
	public void setLoc(LocationDto loc) {
		this.loc = loc;
	}
	
		
}