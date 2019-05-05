package kr.teamapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.teamapp.dao.TeamAppDao;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		int app_num = Integer.parseInt(request.getParameter("app_num"));
		String id = request.getParameter("id");
		TeamAppDao dao = TeamAppDao.getInstance();
		if(dao.CheckApp(id, app_num)) {
			dao.deleteApp(id, app_num);
		}
		
		return "redirect:/team/applyForm.do?team_num="+app_num;
	}
}