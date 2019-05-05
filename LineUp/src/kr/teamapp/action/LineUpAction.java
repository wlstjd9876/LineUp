package kr.teamapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.team.hire.dao.TeamDao;
import kr.teamapp.dao.TeamAppDao;
import kr.util.AuthUtil;

public class LineUpAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		int app_num = Integer.parseInt(request.getParameter("app_num"));
		String id = request.getParameter("id");
		int status = Integer.parseInt(request.getParameter("status"));
		TeamAppDao dao = TeamAppDao.getInstance();
		TeamDao dao2 = TeamDao.getInstance();
		if(dao2.getApplyForm(app_num).getTeam_member()<=dao.getTeamAppCount(app_num)&&status==0) {
			return "redirect:/team/applyForm.do?team_num="+app_num;
		}
		if(status==2) {
			dao.TeamLineup(id, app_num, status);
			dao.sendFinalMemberMsg(app_num);
		}else
			dao.TeamLineup(id, app_num, status);
		
		return "redirect:/team/applyForm.do?team_num="+app_num;
	}
}