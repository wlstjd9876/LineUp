package kr.messenger.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.messenger.dao.MessengerDao;
import kr.util.AuthUtil;

public class CheckAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessengerDao dao = MessengerDao.getInstance();
		int count = dao.checkMessage(AuthUtil.getUser_id(request));
		
		Map<String,Integer> mapAjax = 
				new HashMap<String,Integer>();
			mapAjax.put("count", count);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}