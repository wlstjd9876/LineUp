package kr.messenger.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.messenger.dao.MessengerDao;
import kr.messenger.domain.MessengerDto;
import kr.util.StringUtil;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MessengerDao dao = MessengerDao.getInstance();
		MessengerDto messenger = dao.getMessage(num);
		if(messenger.getCheck_m() == 0) {
			dao.modifyCheck(num);
		}
		
		//¡Ÿ πŸ≤ﬁ √≥∏Æ
		messenger.setContent(StringUtil.useBrNoHtml(messenger.getContent()));
		
		request.setAttribute("messenger", messenger);
		
		return "/WEB-INF/views/message/detail.jsp";
	}

}
