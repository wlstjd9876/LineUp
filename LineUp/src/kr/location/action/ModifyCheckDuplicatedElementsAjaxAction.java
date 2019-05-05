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
		 * JSON �������� ��ȯ�ϱ⸦ ���ϴ� ���ڿ��� HashMap��
		 * key�� value ������ ������ �� ObjectMapper��
		 * writeValueAsString�� Map ��ü�� �����ؼ�
		 * �Ϲ� ���ڿ� �����͸� JSON ������ ���ڿ� �����ͷ�
		 * ��ȯ�ؼ� ��ȯ
		 */
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}