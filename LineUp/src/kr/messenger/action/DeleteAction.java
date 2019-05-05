package kr.messenger.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.messenger.dao.MessengerDao;
import kr.team.hire.dao.TeamDao;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		MessengerDao dao = MessengerDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getMessage(num).getReceiver_id()))
			return "redirect:/message/list.do?num="+num;
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		
		dao.deleteMessage(num);

		return "redirect:/message/list.do?num="+num;
	}
}
