package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//·Î±×¾Æ¿ô
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/main/main.do";
	}

}
