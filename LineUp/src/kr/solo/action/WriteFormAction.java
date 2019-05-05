package kr.solo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.solo.domain.SoloTempDto;
import kr.util.AuthUtil;

public class WriteFormAction implements Action {

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
		SoloDao dao = SoloDao.getInstance();
		String check = request.getParameter("check");
		SoloTempDto temp = null;
		if(check!=null) {
			if(check.equals("1")) {
				temp = dao.checkSoloLineupTemp(AuthUtil.getUser_id(request));
				dao.deleteSoloLineupTemp(AuthUtil.getUser_id(request));
			}else if(check.equals("2")) {
				dao.deleteSoloLineupTemp(AuthUtil.getUser_id(request));
			}
		}
		request.setAttribute("temp", temp);
		
		return "/WEB-INF/views/solo/solo_writeForm.jsp";
	}

}
