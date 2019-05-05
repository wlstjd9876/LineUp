package kr.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.util.AuthUtil;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		MultipartRequest multi = FileUtil.createFile(request);
		int loc_num = Integer.parseInt(multi.getParameter("loc_num"));

		LocationDao dao = LocationDao.getInstance();
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getLocation(loc_num).getLoc_id()))
			return "redirect:/location/detail.do?loc_num="+loc_num;
		//==========글작성자와 로그인한 아이디 체크 끝=========//

		
		String p_ck = multi.getParameter("loc_photo_val");
		LocationDto old_loc = dao.getLocation(loc_num);
		String photo1 = null;
		String photo2 = null;
		if(p_ck!=null) {
			if(!old_loc.getLoc_photo1().equals("no_detail_img.jpg")) {
				FileUtil.removeFile(old_loc.getLoc_photo1());
				FileUtil.removeFile(old_loc.getLoc_photo2());
			}
			photo1 = multi.getFilesystemName("loc_photo1");
			if(photo1!=null) {
				//썸네일 만들기
				photo2 = FileUtil.createThumbnail(photo1, "m"+photo1, 400, 300);
			}else {
				photo1 = "no_detail_img.jpg";
				photo2 = "mno_detail_img.jpg";
			}
		}else {
			photo1 = old_loc.getLoc_photo1();
			photo2 = old_loc.getLoc_photo2();
		}
		

		LocationDto loc = new LocationDto();
		loc.setLoc_num(loc_num);
		loc.setLoc_name(multi.getParameter("loc_name"));
		loc.setLoc_address(multi.getParameter("loc_address"));
		loc.setLoc_phone(multi.getParameter("loc_phone"));
		loc.setLoc_photo1(photo1);
		loc.setLoc_photo2(photo2);
		loc.setLoc_content(multi.getParameter("loc_content"));
		loc.setLoc_id(AuthUtil.getUser_id(request));

		dao.modifyLocation(loc);

		return "/WEB-INF/views/location/loc_modify.jsp";
	}
}