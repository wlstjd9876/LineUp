package kr.util;

public class StringChange {
	public static String C_str(String str, String word, String change) {
		String[] string = str.split(word);
		str = "";
		for(int i=0;i<string.length;i++) {
			if(i==string.length-1)
				str += string[i]+change;
			else
				str += string[i]+change+", ";
		}
		return str;
	}
}