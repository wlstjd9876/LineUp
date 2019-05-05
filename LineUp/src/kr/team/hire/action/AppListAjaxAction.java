package kr.team.hire.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.teamapp.dao.TeamAppDao;
import kr.teamapp.domain.TeamAppDto;
import kr.util.PagingUtil;

public class AppListAjaxAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int app_num = Integer.parseInt(
				         request.getParameter("app_num"));
		int rowCount = 10;
		int pageCount = 10;
		int currentPage = Integer.parseInt(pageNum);
		
		TeamAppDao dao = TeamAppDao.getInstance();
		int count = dao.getAppCount(app_num);
		PagingUtil page = 
				new PagingUtil(currentPage,count,
						rowCount,pageCount,null);
		
		List<TeamAppDto> list = null;
		if(count > 0) {
			list = dao.getListApp(
					page.getStartCount(),
					page.getEndCount(), app_num);
		}else {
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}
