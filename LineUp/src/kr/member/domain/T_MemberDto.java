package kr.member.domain;

public class T_MemberDto{
	private String id;
	private String pw;
	private String phone;
	private String email;
	private String team_name;
	private String team_age;
	private int auth;
	
	public boolean isCheckedPasswd(String userPasswd) {
		if(auth > 0 && pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_age() {
		return team_age;
	}
	public void setTeam_age(String team_age) {
		this.team_age = team_age;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "T_MemberDto [id=" + id + ", pw=" + pw + ", phone=" + phone + ", email=" + email + ", team_name="
				+ team_name + ", team_age=" + team_age + ", auth=" + auth + "]";
	}
	
	
	
}