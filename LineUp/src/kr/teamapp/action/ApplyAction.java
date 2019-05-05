package kr.teamapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.teamapp.dao.TeamAppDao;
import kr.teamapp.domain.TeamAppDto;
import kr.util.AuthUtil;

public class ApplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		TeamAppDao dao = TeamAppDao.getInstance();
		TeamAppDto team = new TeamAppDto();
		team.setApp_num(Integer.parseInt(request.getParameter("app_num").toString()));
		team.setApp_id(AuthUtil.getUser_id(request));
		dao.apply(team);
		
		return "redirect:/team/applyForm.do?team_num="+Integer.parseInt(request.getParameter("app_num").toString());
	}

}
