package kr.team.hire.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.team.hire.dao.TeamDao;
import kr.team.hire.domain.TeamDto;
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

		TeamDao dao = TeamDao.getInstance();
		int count = dao.getTeamCount();
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "list.do");
		
		List<TeamDto> list = null;
		if(count > 0) {
			list = dao.getTeamList(page.getStartCount(), page.getEndCount());	
		}
		
		request.setAttribute("today", today);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/team_hire/team_list.jsp";
	}
}