package kr.solo.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.solo.domain.SoloDto;
import kr.util.AuthUtil;

public class ModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//
		//==========ȸ�� ���� üũ ����=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1 || user_auth==3)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========ȸ�� ���� üũ ��===========//
		int solo_num = Integer.parseInt(request.getParameter("solo_num"));

		SoloDao dao = SoloDao.getInstance();
		SoloDto solo = dao.getApplyForm(solo_num);
		//==========���ۼ��ڿ� �α����� ���̵� üũ ����=========//
		if(!AuthUtil.isAuthWriter(request, solo.getSolo_id()))
			return "redirect:/solo/list.do";
		//==========���ۼ��ڿ� �α����� ���̵� üũ ��=========//
		request.setAttribute("solo", solo);

		return "/WEB-INF/views/solo/solo_modifyForm.jsp";
	}

}
