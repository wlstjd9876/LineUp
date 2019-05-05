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
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		//전송된 데이터 반환
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String user_id = (String)session.getAttribute("user_id");
		int auth = Integer.parseInt(session.getAttribute("user_auth").toString());
		boolean check = false;

		//MemberDao 호출
		if(id.equals(user_id)) {
			if(auth==1) {
				S_MemberDao dao = S_MemberDao.getInstance();;
				S_MemberDto member = dao.getMember(id);
				if(member!=null) {
					//비밀번호 체크
					check = member.isCheckedPasswd(pw);
				}
				if(check)
					dao.deleteMember(user_id);
			}else{
				T_MemberDao dao = T_MemberDao.getInstance();
				T_MemberDto member = dao.getMember(id);
				if(member!=null) {
					//비밀번호 체크
					check = member.isCheckedPasswd(pw);
				}
				if(check)
					dao.deleteMember(user_id);
			}
		}
		if(!check) {
			return "/WEB-INF/views/member/delete.jsp";
		}
		//JSP 경로 지정
		session.invalidate();
		return "/WEB-INF/views/member/deleteS.jsp";
	}
}
