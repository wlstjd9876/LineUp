package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.util.AuthUtil;

public class Solo_infoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//

		HttpSession session = request.getSession();
		
		S_MemberDao dao = S_MemberDao.getInstance();
		S_MemberDto member = dao.getMember((String)session.getAttribute("user_id"));
		if(member.getGen().equals("man")) {
			member.setGen("남자");
		}else {
			member.setGen("여자");
		}
		member.setAge(member.getAge()+"대");
		
		request.setAttribute("member", member);

		return "/WEB-INF/views/member/solo_info.jsp";
	}
}