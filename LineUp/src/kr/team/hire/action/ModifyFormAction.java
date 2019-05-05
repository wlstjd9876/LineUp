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
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//
		//==========ȸ�� ���� üũ ����=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth>1)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========ȸ�� ���� üũ ��===========//
		int team_num = Integer.parseInt(request.getParameter("team_num"));

		TeamDao dao = TeamDao.getInstance();
		TeamDto team = dao.getApplyForm(team_num);
		//==========���ۼ��ڿ� �α����� ���̵� üũ ����=========//
		if(!AuthUtil.isAuthWriter(request, team.getTeam_id()))
			return "redirect:/solo/list.do";
		//==========���ۼ��ڿ� �α����� ���̵� üũ ��=========//
		team.setTeam_etc(StringUtil.useBrNoHtml(team.getTeam_etc()));
		request.setAttribute("team", team);

		return "/WEB-INF/views/team_hire/team_modifyForm.jsp";
	}
}