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
		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======�α��� üũ ��============//
		MessengerDao dao = MessengerDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		//==========���ۼ��ڿ� �α����� ���̵� üũ ����=========//
		if(!AuthUtil.isAuthWriter(request, dao.getMessage(num).getReceiver_id()))
			return "redirect:/message/list.do?num="+num;
		//==========���ۼ��ڿ� �α����� ���̵� üũ ��=========//
		
		dao.deleteMessage(num);

		return "redirect:/message/list.do?num="+num;
	}
}
