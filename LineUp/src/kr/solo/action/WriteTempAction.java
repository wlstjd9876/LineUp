package kr.solo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.solo.dao.SoloDao;
import kr.solo.domain.SoloTempDto;
import kr.util.AuthUtil;

public class WriteTempAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		//==========회원 종류 체크 시작=========//
		int user_auth = AuthUtil.getUser_auth(request);
		if(!(user_auth==1 || user_auth==3)) {
			return "/WEB-INF/views/common/authCheck.jsp";
		}
		//==========회원 종류 체크 끝===========//
		String[] value = request.getParameterValues("solo_age");
		String solo_age = "";
		for(int i=0 ; i<value.length ; i++) {
			solo_age += value[i];
			if(i!=value.length-1)
				solo_age+=",";
		}
		SoloTempDto solo = new SoloTempDto();
		solo.setSolo_min_member(request.getParameter("solo_min_member"));
		solo.setSolo_member(request.getParameter("solo_member"));
		solo.setSolo_end_date(request.getParameter("solo_end_date")+" "+request.getParameter("solo_end_time"));
		solo.setSolo_date(request.getParameter("solo_date")+" "+request.getParameter("solo_time"));
		if(request.getParameter("solo_loc_num")!=null)
			solo.setSolo_loc_num(Integer.parseInt(request.getParameter("solo_loc_num")));
		solo.setSolo_id(AuthUtil.getUser_id(request));
		solo.setSolo_money(request.getParameter("solo_money"));
		solo.setSolo_age(solo_age);
		solo.setSolo_gen(request.getParameter("solo_gen"));
		SoloDao dao = SoloDao.getInstance();
		dao.deleteSoloLineupTemp(AuthUtil.getUser_id(request));
		dao.insertSoloLineupTemp(solo);

		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		mapAjax.put("result", "success");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}

}
