package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class Team_RegisterFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("auth", 2);
		return "/WEB-INF/views/member/team_registerForm.jsp";
	}
}