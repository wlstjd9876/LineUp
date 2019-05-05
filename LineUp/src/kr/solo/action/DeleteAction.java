package kr.solo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1 || user_auth==3)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		SoloDao dao = SoloDao.getInstance();
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getApplyForm(Integer.parseInt(request.getParameter("solo_num"))).getSolo_id()))
			return "redirect:/solo/list.do";
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		
		dao.deleteSoloLineup(Integer.parseInt(request.getParameter("solo_num")));

		return "/WEB-INF/views/solo/solo_delete.jsp";
	}
}
