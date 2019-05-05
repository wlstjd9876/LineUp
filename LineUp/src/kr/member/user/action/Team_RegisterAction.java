package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.T_MemberDao;
import kr.member.domain.T_MemberDto;

public class Team_RegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		T_MemberDto member = new T_MemberDto();
		String[] value = request.getParameterValues("team_age");
		String team_age = "";
		for(int i=0 ; i<value.length ; i++) {
			team_age += value[i];
			if(i!=value.length-1)
				team_age+=",";
		}
		
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setTeam_name(request.getParameter("team_name"));
		member.setTeam_age(team_age);

		T_MemberDao dao = T_MemberDao.getInstance();
		dao.insertMember(member);

		return "/WEB-INF/views/member/registerUser.jsp";
	}
}