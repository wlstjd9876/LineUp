package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.T_MemberDao;
import kr.member.domain.T_MemberDto;
import kr.util.AuthUtil;
import kr.util.StringChange;

public class Team_infoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======�α��� üũ ��============//

		HttpSession session = request.getSession();
		
		T_MemberDao dao = T_MemberDao.getInstance();
		T_MemberDto member = dao.getMember((String)session.getAttribute("user_id"));
		member.setTeam_age(StringChange.C_str(member.getTeam_age(), ",", "��"));
		
		request.setAttribute("member", member);

		return "/WEB-INF/views/member/team_info.jsp";
	}
}