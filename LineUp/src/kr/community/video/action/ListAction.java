package kr.community.video.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.community.video.dao.Com_VideoDao;
import kr.community.video.domain.Com_VideoDto;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		if(keyfield == null) keyfield = "";
		if(keyword == null) keyword="";
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		
		int rowCount = 6; //한 페이지의 게시물 수
		int pageCount = 10; //한 화면의 페이지 수
		int currentPage = Integer.parseInt(pageNum);

		Com_VideoDao dao = Com_VideoDao.getInstance();
		int count = dao.getVideoCount(keyfield, keyword);
		
		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "list.do");
		
		List<Com_VideoDto> list = null;
		if(count > 0) {
			list = dao.getVideoList(page.getStartCount(), page.getEndCount(), keyfield, keyword);					
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/community/video/list.jsp";
	}
}