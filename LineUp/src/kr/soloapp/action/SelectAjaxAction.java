package kr.soloapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.soloapp.dao.SoloAppDao;

public class SelectAjaxAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int app_num = Integer.parseInt(request.getParameter("num"));
		
		SoloAppDao dao = SoloAppDao.getInstance();
		SoloDao dao1 = SoloDao.getInstance();
		int count = dao.getSoloAppCount(app_num);
		int member = dao1.getApplyForm(app_num).getSolo_member()-count;
		
		Map<String,Integer> mapAjax = 
				new HashMap<String,Integer>();
		mapAjax.put("member", member);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}




