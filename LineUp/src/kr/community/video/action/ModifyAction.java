package kr.community.video.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.community.video.dao.Com_VideoDao;
import kr.community.video.domain.Com_VideoDto;
import kr.controller.Action;
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
		int num = Integer.parseInt(multi.getParameter("num"));

		Com_VideoDao dao = Com_VideoDao.getInstance();
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.isAuthWriter(request, dao.getVideo(num).getId()))
			return "redirect:/com_video/detail.do?loc_num="+num;
		//==========글작성자와 로그인한 아이디 체크 끝=========//


		String photo = multi.getFilesystemName("thumb");

		String thumb = FileUtil.createThumbnail(photo, "m"+photo, 400, 300);
		FileUtil.removeFile(photo);
		
		Com_VideoDto dbVideo = dao.getVideo(num);
		


		Com_VideoDto video = new Com_VideoDto();
		video.setTitle(multi.getParameter("title"));
		video.setContent(multi.getParameter("content"));
		video.setIp(request.getRemoteAddr());
		video.setId(AuthUtil.getUser_id(request));
		video.setThumb(thumb);
		video.setNum(num);

		dao.insertVideo(video);
		
		if(video.getThumb()!=null)
			FileUtil.removeFile(dbVideo.getThumb());

		return "/WEB-INF/views/community/video/modify.jsp";
	}
}