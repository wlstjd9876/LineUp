package kr.team.hire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.team.hire.dao.TeamDao;
import kr.team.hire.domain.TeamDto;
import kr.util.AuthUtil;
import kr.util.StringUtil;

public class ModifyAction implements Action {

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
		TeamDao dao = TeamDao.getInstance();
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getApplyForm(Integer.parseInt(request.getParameter("team_num"))).getTeam_id()))
			return "redirect:/team/list.do";
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		String[] value = request.getParameterValues("team_age");
		String team_age = "";
		for(int i=0 ; i<value.length ; i++) {
			team_age += value[i];
			if(i!=value.length-1)
				team_age+=",";
		}

		TeamDto team = new TeamDto();
		team.setTeam_id(AuthUtil.getUser_id(request));
		team.setTeam_loc_num(Integer.parseInt(request.getParameter("team_loc_num")));
		team.setTeam_member(Integer.parseInt(request.getParameter("team_member")));
		team.setTeam_end_date(request.getParameter("team_end_date")+" "+request.getParameter("team_end_time"));
		team.setTeam_date(request.getParameter("team_date")+" "+request.getParameter("team_time"));
		team.setTeam_age(team_age);
		if(!request.getParameter("team_money").equals(""))
			team.setTeam_money(Integer.parseInt(request.getParameter("team_money")));
		team.setTeam_etc(StringUtil.useBrNoHtml(request.getParameter("team_etc")));
		dao.modifyTeamLineup(team);

		return "/WEB-INF/views/solo/solo_modify.jsp";
	}

}
