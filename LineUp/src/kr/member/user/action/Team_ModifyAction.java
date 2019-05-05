package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.T_MemberDao;
import kr.member.domain.T_MemberDto;
import kr.util.AuthUtil;

public class Team_ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		
		String[] value = request.getParameterValues("team_age");
		String team_age = "";
		for(int i=0 ; i<value.length ; i++) {
			team_age += value[i];
			if(i!=value.length-1)
				team_age+=",";
		}

		T_MemberDao dao = T_MemberDao.getInstance();
		T_MemberDto member = new T_MemberDto();
		member.setId(request.getParameter("id"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setTeam_name(request.getParameter("team_name"));
		member.setTeam_age(team_age);
		
		dao.modifyMember(member);

		return "/WEB-INF/views/member/modify.jsp";
	}

}




