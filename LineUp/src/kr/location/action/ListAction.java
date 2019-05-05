package kr.location.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
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

		LocationDao dao = LocationDao.getInstance();
		//상태값과 관계 없이 모든 상품 표시 status는 0으로 전달
		int count = dao.getLocationCount(keyfield, keyword);
		
		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "list.do");
		
		List<LocationDto> list = null;
		if(count > 0) {
			list = dao.getLocationList(page.getStartCount(), page.getEndCount(), keyfield, keyword);					
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/location/loc_list.jsp";
	}
}