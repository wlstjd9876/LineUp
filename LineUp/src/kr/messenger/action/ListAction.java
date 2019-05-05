package kr.messenger.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.messenger.dao.MessengerDao;
import kr.messenger.domain.MessengerDto;
import kr.util.AuthUtil;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		
		int rowCount = 5; //한 페이지의 게시물 수
		int pageCount = 10; //한 화면의 페이지 수
		int currentPage = Integer.parseInt(pageNum);

		MessengerDao dao = MessengerDao.getInstance();
		int count = dao.getSoloCount("receiver_id", AuthUtil.getUser_id(request));
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "list.do");
		
		List<MessengerDto> list = null;
		if(count > 0) {
			list = dao.getMessengerList(page.getStartCount(), page.getEndCount(),"receiver_id",AuthUtil.getUser_id(request));	
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/message/list.jsp";
	}
}