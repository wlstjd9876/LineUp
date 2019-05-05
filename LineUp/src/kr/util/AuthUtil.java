package kr.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtil {
	//�α��� ���� üũ
	public static boolean isLogin(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = 
				(String)session.getAttribute("user_id");
		//�α��� üũ
		if(user_id==null) {
			return false;
		}
		return true;
	}
	//������ ���� üũ
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

	//�α����� ���̵� ��ȯ
	public static String getUser_id(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String)session.getAttribute("user_id");
	}
	//�α����� ȸ�� ���� ��ȯ
	public static int getUser_auth(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		return Integer.parseInt(session.getAttribute("user_auth").toString());
	}
	//�α��� �� ID�� �ۼ��� ID ��ġ ���� üũ
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
		//���� ����
		FileUtil.removeFile(filename);
		return false;
	}
}










