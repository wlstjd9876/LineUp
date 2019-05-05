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