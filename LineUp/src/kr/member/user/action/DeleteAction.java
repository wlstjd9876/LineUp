package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.dao.T_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.member.domain.T_MemberDto;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======�α��� üũ ��============//
		//���۵� ������ ��ȯ
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String user_id = (String)session.getAttribute("user_id");
		int auth = Integer.parseInt(session.getAttribute("user_auth").toString());
		boolean check = false;

		//MemberDao ȣ��
		if(id.equals(user_id)) {
			if(auth==1) {
				S_MemberDao dao = S_MemberDao.getInstance();;
				S_MemberDto member = dao.getMember(id);
				if(member!=null) {
					//��й�ȣ üũ
					check = member.isCheckedPasswd(pw);
				}
				if(check)
					dao.deleteMember(user_id);
			}else{
				T_MemberDao dao = T_MemberDao.getInstance();
				T_MemberDto member = dao.getMember(id);
				if(member!=null) {
					//��й�ȣ üũ
					check = member.isCheckedPasswd(pw);
				}
				if(check)
					dao.deleteMember(user_id);
			}
		}
		if(!check) {
			return "/WEB-INF/views/member/delete.jsp";
		}
		//JSP ��� ����
		session.invalidate();
		return "/WEB-INF/views/member/deleteS.jsp";
	}
}
