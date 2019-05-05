package kr.member.user.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.solo.domain.SoloDto;
import kr.team.hire.domain.TeamDto;
import kr.util.AuthUtil;
import kr.util.PagingUtil;

public class Solo_ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageNum1 = request.getParameter("pageNum");
		String pageNum2 = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		if(pageNum1==null)pageNum1 = "1";
		if(pageNum2==null)pageNum2 = "1";
		
		int rowCount = 2; //한 페이지의 게시물 수
		int pageCount = 10; //한 화면의 페이지 수
		int currentPage = Integer.parseInt(pageNum);
		int rowCount1 = 2; //한 페이지의 게시물 수
		int pageCount1 = 10; //한 화면의 페이지 수
		int currentPage1 = Integer.parseInt(pageNum1);
		int rowCount2 = 2; //한 페이지의 게시물 수
		int pageCount2 = 10; //한 화면의 페이지 수
		int currentPage2 = Integer.parseInt(pageNum2);
		
		String id = AuthUtil.getUser_id(request);
		S_MemberDao dao = S_MemberDao.getInstance();
		int count = dao.getSoloCount(id);
		int spcount = dao.getSoloappCount(id);
		int tpcount = dao.getTeamappCount(id);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "solo_writelist.do");
		PagingUtil page1 = new PagingUtil(currentPage1, spcount, rowCount1, pageCount1, "solo_writelist.do");
		PagingUtil page2 = new PagingUtil(currentPage2, tpcount, rowCount2, pageCount2, "solo_writelist.do");
		
		List<SoloDto> list = null;
		if(count > 0) {
			list = dao.getWriteList(page.getStartCount(), page.getEndCount(), id);	
		}
		List<SoloDto> list1 = null;
		if(spcount > 0) {
			list1 = dao.getAppList(page1.getStartCount(), page1.getEndCount(), id);	
		}
		List<TeamDto> list2 = null;
		if(tpcount > 0) {
			list2 = dao.getTeamAppList(page2.getStartCount(), page2.getEndCount(), id);	
		}
		request.setAttribute("count", count);
		request.setAttribute("spcount", spcount);
		request.setAttribute("tpcount", tpcount);
		request.setAttribute("list", list);
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		request.setAttribute("pagingHtml1", page1.getPagingHtml());
		request.setAttribute("pagingHtml2", page2.getPagingHtml());
		
		return "/WEB-INF/views/member/solo_list.jsp";
	}
}