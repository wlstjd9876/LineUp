package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;

public class Solo_RegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		S_MemberDto member = new S_MemberDto();
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setGen(request.getParameter("gen"));
		member.setAge(request.getParameter("age"));
		member.setNick(request.getParameter("nick"));

		S_MemberDao dao = S_MemberDao.getInstance();
		dao.insertMember(member);

		return "/WEB-INF/views/member/registerUser.jsp";
	}
}