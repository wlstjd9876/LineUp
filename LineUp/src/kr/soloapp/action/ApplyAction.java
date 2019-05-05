package kr.soloapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.soloapp.dao.SoloAppDao;
import kr.soloapp.domain.SoloAppDto;
import kr.util.AuthUtil;

public class ApplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//

		//==========ȸ�� ���� üũ ����=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========ȸ�� ���� üũ ��===========//
		SoloAppDao dao = SoloAppDao.getInstance();
		SoloAppDto solo = new SoloAppDto();
		solo.setApp_num(Integer.parseInt(request.getParameter("app_num").toString()));
		solo.setApp_id(AuthUtil.getUser_id(request));
		solo.setApp_member(Integer.parseInt(request.getParameter("app_member").toString()));
		dao.apply(solo);
		
		return "redirect:/solo/applyForm.do?solo_num="+Integer.parseInt(request.getParameter("app_num").toString());
	}

}
