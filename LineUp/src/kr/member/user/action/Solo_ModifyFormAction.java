package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.util.AuthUtil;

public class Solo_ModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		
		S_MemberDao dao = S_MemberDao.getInstance();
		HttpSession session = request.getSession();
		S_MemberDto member = 
				dao.getMember(
			(String)session.getAttribute("user_id"));
		
		request.setAttribute("member", member);
		
		return "/WEB-INF/views/member/solo_ModifyForm.jsp";
	}
}