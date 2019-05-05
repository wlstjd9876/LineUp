package kr.messenger.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.messenger.dao.MessengerDao;
import kr.messenger.domain.MessengerDto;
import kr.util.AuthUtil;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//
		MessengerDto messenger = new MessengerDto();
		MessengerDao dao = MessengerDao.getInstance();
		messenger.setTitle(request.getParameter("title"));
		messenger.setContent(request.getParameter("content"));
		messenger.setSender_id(AuthUtil.getUser_id(request));
		messenger.setReceiver_id(request.getParameter("receiver_id"));
		dao.sendMessage(messenger);
		
		return "/WEB-INF/views/message/write.jsp";
	}
}