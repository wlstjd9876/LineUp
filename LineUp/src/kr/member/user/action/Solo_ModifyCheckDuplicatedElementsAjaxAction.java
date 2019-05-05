package kr.member.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;

public class Solo_ModifyCheckDuplicatedElementsAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String nick = request.getParameter("nick");
		S_MemberDao dao = S_MemberDao.getInstance();
		S_MemberDto member = dao.getMember(id);
		boolean check = false;
		if(phone!=null) {
			check = dao.checkDuplicated("phone", phone);
			if(check) {
				if(phone.equals(member.getPhone())) {
					check = false;
				}
			}
		}else if(email!=null) {
			check = dao.checkDuplicated("email", email);
			if(check) {
				if(email.equals(member.getEmail())) {
					check = false;
				}
			}
		}else if(nick!=null) {
			check = dao.checkDuplicated("nick", nick);
			if(check) {
				if(nick.equals(member.getNick())) {
					check = false;
				}
			}
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