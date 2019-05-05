package kr.team.hire.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.team.hire.dao.TeamDao;
import kr.team.hire.domain.TeamDto;
import kr.util.AuthUtil;
import kr.util.StringUtil;

public class ModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth>1)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		int team_num = Integer.parseInt(request.getParameter("team_num"));

		TeamDao dao = TeamDao.getInstance();
		TeamDto team = dao.getApplyForm(team_num);
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, team.getTeam_id()))
			return "redirect:/solo/list.do";
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		team.setTeam_etc(StringUtil.useBrNoHtml(team.getTeam_etc()));
		request.setAttribute("team", team);

		return "/WEB-INF/views/team_hire/team_modifyForm.jsp";
	}
}