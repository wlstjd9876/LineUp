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
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		SoloAppDao dao = SoloAppDao.getInstance();
		SoloAppDto solo = new SoloAppDto();
		solo.setApp_num(Integer.parseInt(request.getParameter("app_num").toString()));
		solo.setApp_id(AuthUtil.getUser_id(request));
		solo.setApp_member(Integer.parseInt(request.getParameter("app_member").toString()));
		dao.apply(solo);
		
		return "redirect:/solo/applyForm.do?solo_num="+Integer.parseInt(request.getParameter("app_num").toString());
	}

}
