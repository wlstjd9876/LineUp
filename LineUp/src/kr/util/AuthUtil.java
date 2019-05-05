package kr.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtil {
	//로그인 여부 체크
	public static boolean isLogin(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = 
				(String)session.getAttribute("user_id");
		//로그인 체크
		if(user_id==null) {
			return false;
		}
		return true;
	}
	//관리자 권한 체크
	public static boolean isAuthAdmin(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = 
				(String)session.getAttribute("user_id");
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_id!=null && user_auth !=3) {
			return false;
		}
		return true;
	}

	//로그인한 아이디 반환
	public static String getUser_id(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String)session.getAttribute("user_id");
	}
	//로그인한 회원 종류 반환
	public static int getUser_auth(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		return Integer.parseInt(session.getAttribute("user_auth").toString());
	}
	//로그인 한 ID와 작성자 ID 일치 여부 체크
	public static boolean isAuthWriter(
			HttpServletRequest request, String writer) {
		HttpSession session = request.getSession();
		String user_id = 
				(String)session.getAttribute("user_id");
		int user_auth = Integer.parseInt(session.getAttribute("user_auth").toString());
		if((user_id!=null && user_id.equals(writer))||user_auth == 3) {
			return true;
		}
		return false;
	}

	public static boolean isAuthWriter(
			HttpServletRequest request, 
			String writer,
			String filename) {
		HttpSession session = request.getSession();
		String user_id = 
				(String)session.getAttribute("user_id");
		if(user_id!=null && user_id.equals(writer)) {
			return true;
		}
		//파일 삭제
		FileUtil.removeFile(filename);
		return false;
	}
}










