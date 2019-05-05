package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.dao.T_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.member.domain.T_MemberDto;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//���۵� ������ ��ȯ
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int auth = Integer.parseInt(request.getParameter("auth"));
		boolean check = false;
		//MemberDao ȣ��
		if(auth==1 || auth==3) {
			S_MemberDao dao = S_MemberDao.getInstance();
			S_MemberDto member = dao.getMember(id);
			if(member!=null) {
				//��й�ȣ üũ
				check = member.isCheckedPasswd(pw);
				if(member.getAuth()==3)
					auth=3;
			}
		}else{
			T_MemberDao dao = T_MemberDao.getInstance();
			T_MemberDto member = dao.getMember(id);
			if(member!=null) {
				//��й�ȣ üũ
				check = member.isCheckedPasswd(pw);
			}
		}
		
		if(check) {//���� ����
			//�α��� ó��
			HttpSession session = request.getSession();
			session.setAttribute("user_id", id);
			session.setAttribute("user_auth", auth);
		}
		
		request.setAttribute("check", check);
		
		//JSP ��� ����
		return "/WEB-INF/views/member/login.jsp";
	}

}


