package kr.location.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.util.AuthUtil;
import kr.util.FileUtil;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		String photo1 = multi.getFilesystemName("loc_photo1");
		
		String photo2 = null;
		if(photo1!=null) {
			//썸네일 만들기
			photo2 = FileUtil.createThumbnail(photo1, "m"+photo1, 400, 300);
		}else {
			photo1 = "no_detail_img.jpg";
			photo2 = "mno_detail_img.jpg";
		}
		LocationDto loc = new LocationDto();
		loc.setLoc_name(multi.getParameter("loc_name"));
		loc.setLoc_address(multi.getParameter("loc_address"));
		loc.setLoc_phone(multi.getParameter("loc_phone"));
		loc.setLoc_photo1(photo1);
		loc.setLoc_photo2(photo2);
		loc.setLoc_content(multi.getParameter("loc_content"));
		loc.setLoc_id(AuthUtil.getUser_id(request));
		
		LocationDao dao = LocationDao.getInstance();
		dao.insertLocation(loc);
		
		return "/WEB-INF/views/location/loc_write.jsp";
	}
}