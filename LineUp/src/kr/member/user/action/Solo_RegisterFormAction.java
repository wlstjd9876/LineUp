package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class Solo_RegisterFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("auth", 1);
			return "/WEB-INF/views/member/solo_registerForm.jsp";
	}
}