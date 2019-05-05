package kr.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.util.AuthUtil;
import kr.util.StringUtil;

public class ModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		int loc_num = Integer.parseInt(request.getParameter("loc_num"));

		LocationDao dao = LocationDao.getInstance();
		LocationDto loc = dao.getLocation(loc_num);
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, loc.getLoc_id()))
			return "redirect:/location/detail.do?loc_num="+loc_num;
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		loc.setLoc_content(StringUtil.useBrHtml(loc.getLoc_content()));
		request.setAttribute("loc", loc);

		return "/WEB-INF/views/location/loc_modifyForm.jsp";
	}

}
