package kr.member.domain;

public class S_MemberDto{
	private String id;
	private String pw;
	private String phone;
	private String email;
	private String name;
	private String gen;
	private String age;
	private int auth;
	private String nick;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	@Override
	public String toString() {
		return "S_MemberDto [id=" + id + ", pw=" + pw + ", phone=" + phone + ", email=" + email + ", name=" + name
				+ ", gen=" + gen + ", age=" + age + ", auth=" + auth + ", nick=" + nick + "]";
	}
}