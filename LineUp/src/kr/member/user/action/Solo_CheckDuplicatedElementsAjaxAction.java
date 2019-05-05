package kr.member.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.dao.T_MemberDao;

public class Solo_CheckDuplicatedElementsAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String nick = request.getParameter("nick");
		
		S_MemberDao dao = S_MemberDao.getInstance();
		T_MemberDao dao1 = T_MemberDao.getInstance();
		boolean check = false;
		if(id!=null) {
			if(dao.getMember(id)!=null || dao1.getMember(id)!=null) check = true;
		}else if(phone!=null) {
			check = dao.checkDuplicated("phone", phone);
		}else if(email!=null) {
			check = dao.checkDuplicated("email", email);
		}else if(nick!=null) {
			check = dao.checkDuplicated("nick", nick);
		}
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(check) {
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



