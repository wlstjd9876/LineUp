package kr.solo.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.solo.domain.SoloDto;
import kr.util.AuthUtil;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date time = new Date();
		String today = format1.format(time);
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		
		int rowCount = 5; //한 페이지의 게시물 수
		int pageCount = 10; //한 화면의 페이지 수
		int currentPage = Integer.parseInt(pageNum);

		SoloDao dao = SoloDao.getInstance();
		int count = dao.getSoloCount();
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "list.do");
		
		List<SoloDto> list = null;
		if(count > 0) {
			list = dao.getSoloList(page.getStartCount(), page.getEndCount());	
		}
		String check = "";
		if(dao.checkSoloLineupTemp(AuthUtil.getUser_id(request))==null) {
			check = "false";
		}else {
			check = "true";
		}
		
		request.setAttribute("check",check );
		request.setAttribute("today", today);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/solo/solo_list.jsp";
	}
}