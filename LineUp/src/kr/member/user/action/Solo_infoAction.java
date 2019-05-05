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

		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======�α��� üũ ��============//

		HttpSession session = request.getSession();
		
		S_MemberDao dao = S_MemberDao.getInstance();
		S_MemberDto member = dao.getMember((String)session.getAttribute("user_id"));
		if(member.getGen().equals("man")) {
			member.setGen("����");
		}else {
			member.setGen("����");
		}
		member.setAge(member.getAge()+"��");
		
		request.setAttribute("member", member);

		return "/WEB-INF/views/member/solo_info.jsp";
	}
}