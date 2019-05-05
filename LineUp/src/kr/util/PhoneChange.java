package kr.util;

public class PhoneChange {
	public static String phone(String phone) {
		String ex=phone;
		String change;
		
		ex = ex.substring(0,2);
		ex += "-"+phone.substring(4);
		
		
		
		
		
		return phone;
	}
}
