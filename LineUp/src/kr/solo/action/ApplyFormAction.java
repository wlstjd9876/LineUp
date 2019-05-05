package kr.solo.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.solo.dao.SoloDao;
import kr.solo.domain.SoloDto;
import kr.soloapp.dao.SoloAppDao;
import kr.util.AuthUtil;

public class ApplyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1 ||user_auth==3)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date time = new Date();
		String today = format1.format(time);
		
		int solo_num = Integer.parseInt(request.getParameter("solo_num"));
		SoloDao dao = SoloDao.getInstance();
		SoloDto solo = dao.getApplyForm(solo_num);
		S_MemberDao dao1 = S_MemberDao.getInstance();
		S_MemberDto member = dao1.getMember(AuthUtil.getUser_id(request));
		SoloAppDao dao2 = SoloAppDao.getInstance();
		
		request.setAttribute("today", today);
		request.setAttribute("solo", solo);
		request.setAttribute("member", member);
		request.setAttribute("check", dao2.CheckApp(AuthUtil.getUser_id(request), solo_num));
		
		return "/WEB-INF/views/solo/solo_applyForm.jsp";
	}

}
