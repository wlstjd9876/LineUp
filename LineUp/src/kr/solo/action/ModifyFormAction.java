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
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1 || user_auth==3)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		int solo_num = Integer.parseInt(request.getParameter("solo_num"));

		SoloDao dao = SoloDao.getInstance();
		SoloDto solo = dao.getApplyForm(solo_num);
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, solo.getSolo_id()))
			return "redirect:/solo/list.do";
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		request.setAttribute("solo", solo);

		return "/WEB-INF/views/solo/solo_modifyForm.jsp";
	}

}
