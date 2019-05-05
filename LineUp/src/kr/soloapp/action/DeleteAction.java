package kr.soloapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.soloapp.dao.SoloAppDao;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======�α��� üũ ��============//
		int app_num = Integer.parseInt(request.getParameter("app_num"));
		String id = request.getParameter("id");
		SoloAppDao dao = SoloAppDao.getInstance();
		if(dao.CheckApp(id, app_num)) {
			dao.deleteApp(id, app_num);
		}
		
		return "redirect:/solo/applyForm.do?solo_num="+app_num;
	}
}
