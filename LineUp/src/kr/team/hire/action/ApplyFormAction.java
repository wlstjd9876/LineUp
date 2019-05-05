package kr.team.hire.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.team.hire.dao.TeamDao;
import kr.team.hire.domain.TeamDto;
import kr.teamapp.dao.TeamAppDao;
import kr.util.AuthUtil;

public class ApplyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth>0)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String today = format1.format(time);
		
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		TeamDao dao = TeamDao.getInstance();
		TeamDto team = dao.getApplyForm(team_num);
		S_MemberDto member = null;
		if(user_auth==1) {
			S_MemberDao dao1 = S_MemberDao.getInstance();
			member = dao1.getMember(AuthUtil.getUser_id(request));
		}
		TeamAppDao dao2 = TeamAppDao.getInstance();
		boolean lineup = dao2.checkStatus(team_num);
		
		
		request.setAttribute("lineup", lineup);
		request.setAttribute("member", member);
		request.setAttribute("today", today);
		request.setAttribute("team", team);
		request.setAttribute("check", dao2.CheckApp(AuthUtil.getUser_id(request), team_num));
		
		
		return "/WEB-INF/views/team_hire/team_applyForm.jsp";
	}

}
