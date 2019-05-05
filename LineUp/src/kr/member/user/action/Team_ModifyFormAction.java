package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.T_MemberDao;
import kr.member.domain.T_MemberDto;
import kr.util.AuthUtil;

public class Team_ModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		
		T_MemberDao dao = T_MemberDao.getInstance();
		HttpSession session = request.getSession();
		T_MemberDto member = 
				dao.getMember(
			(String)session.getAttribute("user_id"));
		
		request.setAttribute("member", member);
		
		return "/WEB-INF/views/member/team_ModifyForm.jsp";
	}

}




