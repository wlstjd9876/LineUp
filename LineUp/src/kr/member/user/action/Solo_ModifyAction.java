package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.util.AuthUtil;

public class Solo_ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//

		S_MemberDao dao = S_MemberDao.getInstance();
		S_MemberDto member = new S_MemberDto();
		member.setId(request.getParameter("id"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setGen(request.getParameter("gen"));
		member.setAge(request.getParameter("age"));
		member.setNick(request.getParameter("nick"));
		
		dao.modifyMember(member);

		return "/WEB-INF/views/member/modify.jsp";
	}

}




