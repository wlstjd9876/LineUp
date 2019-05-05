package kr.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.solo.dao.SoloDao;
import kr.team.hire.dao.TeamDao;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======�α��� üũ ��============//
		int loc_num = Integer.parseInt(request.getParameter("loc_num"));
		LocationDao dao = LocationDao.getInstance();
		SoloDao dao1 = SoloDao.getInstance();
		TeamDao dao2 = TeamDao.getInstance();
		LocationDto loc = dao.getLocation(loc_num);
		//==========���ۼ��ڿ� �α����� ���̵� üũ ����=========//
		if(!AuthUtil.getUser_id(request).equals(loc.getLoc_id()))
			return "redirect:/location/detail.do?loc_num="+loc_num;
		//==========���ۼ��ڿ� �α����� ���̵� üũ ��=========//
		dao1.deleteLocation(loc_num);
		dao2.deleteLocation(loc_num);
		dao.deleteLocation(loc);
		return "/WEB-INF/views/location/loc_delete.jsp";
	}
}
