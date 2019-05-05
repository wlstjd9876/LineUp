package kr.team.hire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.team.hire.dao.TeamDao;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth>1)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		TeamDao dao = TeamDao.getInstance();
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getApplyForm(Integer.parseInt(request.getParameter("team_num"))).getTeam_id()))
			return "redirect:/team/hirelist.do";
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		
		dao.deleteTeamLineup(Integer.parseInt(request.getParameter("team_num")));

		return "/WEB-INF/views/team_hire/team_delete.jsp";
	}
}
