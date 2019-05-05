package kr.solo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.util.StringUtil;

public class SearchDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//��ǰ ��ȣ ��ȯ
		int loc_num = Integer.parseInt(request.getParameter("loc_num"));
		
		LocationDao dao = LocationDao.getInstance();
		LocationDto loc = dao.getLocation(loc_num);
		
		//�� �ٲ� ó��
		loc.setLoc_content(StringUtil.useBrNoHtml(loc.getLoc_content()));
		
		request.setAttribute("loc", loc);
		
		return "/WEB-INF/views/solo/solo_search_detail_popup.jsp";
	}

}
