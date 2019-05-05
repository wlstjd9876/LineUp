package kr.solo.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;

public class DateCheckAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] date_s = null;
		String[] end_date_s = null;
		String date = null;
		String end_date = null;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String today = dt.format(time);
		String[] today_s = today.split("-");
		
		if(!request.getParameter("solo_date").equals("")) {
			date = request.getParameter("solo_date");
			date_s = date.split("-");
		}
		if(!request.getParameter("solo_end_date").equals("")) {
			end_date = request.getParameter("solo_end_date");
			end_date_s = end_date.split("-");
		}
		String ck = "pass";
		if(date_s != null) {
			for(int i=0 ; i<date_s.length ; i++) {
				ck = "date_todayDate";
				int diff = Integer.parseInt(date_s[i])-Integer.parseInt(today_s[i]);
				if(diff<0) {
					ck = "date_todayDate";
					break;
				}else if(diff>0) {
					ck = "pass";
					break;
				}
			}
		}
		
		if(date_s != null && end_date_s !=null) {
			for(int i=0 ; i<date_s.length ; i++) {
				ck = "endDate";
				int diff = Integer.parseInt(end_date_s[i]) - Integer.parseInt(date_s[i]);
				if(diff>0) {
					ck = "endDate";
					break;
				}else if(diff < 0){
					ck = "pass";
					break;
				}
			}
		}
		
		if(end_date_s != null && ck.equals("pass")) {
			for(int i=0 ; i<end_date_s.length ; i++) {
				ck = "end_todayDate";
				int diff = Integer.parseInt(end_date_s[i])-Integer.parseInt(today_s[i]);
				if(diff<0) {
					ck = "end_todayDate";
					break;
				}else if(diff>0) {
					ck = "pass";
					break;
				}
			}
		}
		
		if(date_s==null && end_date_s==null){
			ck = "none";
		}
		System.out.println(ck);
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(ck.equals("pass")) {
			mapAjax.put("result", "pass");
		}else if(ck.equals("endDate")) {
			mapAjax.put("result", "endDate");
		}else if(ck.equals("date_todayDate")){
			mapAjax.put("result", "date_todayDate");
		}else if(ck.equals("end_todayDate")){
			mapAjax.put("result", "end_todayDate");
		}else if(ck.equals("none")){
			mapAjax.put("result", "none");
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}
}