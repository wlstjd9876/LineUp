package kr.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.util.StringUtil;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int loc_num = Integer.parseInt(request.getParameter("loc_num"));
		
		LocationDao dao = LocationDao.getInstance();
		LocationDto loc = dao.getLocation(loc_num);
		
		//¡Ÿ πŸ≤ﬁ √≥∏Æ
		loc.setLoc_content(StringUtil.useBrNoHtml(loc.getLoc_content()));
		
		request.setAttribute("loc", loc);
		
		return "/WEB-INF/views/location/loc_detail.jsp";
	}

}
