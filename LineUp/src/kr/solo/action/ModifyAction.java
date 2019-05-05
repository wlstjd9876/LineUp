package kr.solo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.solo.domain.SoloDto;
import kr.util.AuthUtil;

public class ModifyAction implements Action {

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
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getApplyForm(Integer.parseInt(request.getParameter("solo_num"))).getSolo_id()))
			return "redirect:/solo/list.do";
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		String[] value = request.getParameterValues("solo_age");
		String solo_age = "";
		for(int i=0 ; i<value.length ; i++) {
			solo_age += value[i];
			if(i!=value.length-1)
				solo_age+=",";
		}

		SoloDto solo = new SoloDto();
		solo.setSolo_min_member(Integer.parseInt(request.getParameter("solo_min_member")));
		solo.setSolo_member(Integer.parseInt(request.getParameter("solo_member")));
		solo.setSolo_end_date(request.getParameter("solo_end_date")+" "+request.getParameter("solo_end_time"));
		solo.setSolo_date(request.getParameter("solo_date")+" "+request.getParameter("solo_time"));
		solo.setSolo_loc_num(Integer.parseInt(request.getParameter("solo_loc_num")));
		solo.setSolo_id(AuthUtil.getUser_id(request));
		if(!request.getParameter("solo_money").equals(""))
			solo.setSolo_money(Integer.parseInt(request.getParameter("solo_money")));
		solo.setSolo_age(solo_age);
		solo.setSolo_gen(request.getParameter("solo_gen"));
		solo.setSolo_num(Integer.parseInt(request.getParameter("solo_num")));
		dao.modifySoloLineup(solo);

		return "/WEB-INF/views/solo/solo_modify.jsp";
	}

}
