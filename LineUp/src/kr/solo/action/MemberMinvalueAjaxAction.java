package kr.solo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;

public class MemberMinvalueAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int member = 0;
		int min_member = 0;
		if(!request.getParameter("solo_member").equals(""))
			member = Integer.parseInt(request.getParameter("solo_member").toString());
		if(!request.getParameter("solo_min_member").equals(""))
		 min_member = Integer.parseInt(request.getParameter("solo_min_member").toString());
		String ck = "";
		if(member<=min_member){
			ck = "under";
		}else if(member<1) {
			ck = "zero";
		}
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(ck.equals("zero")) {
			mapAjax.put("result", "zero");
		}else if(ck.equals("under")) {
			mapAjax.put("result", "under");
		}else {
			mapAjax.put("result", "pass");
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}