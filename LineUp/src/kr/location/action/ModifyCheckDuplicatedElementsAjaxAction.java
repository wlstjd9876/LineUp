package kr.location.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.util.AuthUtil;

public class ModifyCheckDuplicatedElementsAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loc_address = request.getParameter("loc_address");
		int loc_num = Integer.parseInt(request.getParameter("user_loc_num"));
		LocationDao dao = LocationDao.getInstance();
		boolean check = dao.checkDuplicated(loc_address);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(check && !loc_address.equals(dao.getLocation(loc_num).getLoc_address())) {
			mapAjax.put("result", "Duplicated");
		}else {
			mapAjax.put("result", "NotFound");
		}
		
		/*
		 * JSON 형식으로 변환하기를 원하는 문자열을 HashMap에
		 * key와 value 쌍으로 저장한 후 ObjectMapper의
		 * writeValueAsString에 Map 객체를 전달해서
		 * 일반 문자열 데이터를 JSON 형식의 문자열 데이터로
		 * 변환해서 반환
		 */
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}