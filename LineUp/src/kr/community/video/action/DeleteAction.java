package kr.community.video.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.community.video.dao.Com_VideoDao;
import kr.community.video.domain.Com_VideoDto;
import kr.controller.Action;
import kr.util.AuthUtil;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//========로그인 체크 시작==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=======로그인 체크 끝============//
		int num = Integer.parseInt(request.getParameter("num"));
		Com_VideoDao dao = Com_VideoDao.getInstance();
		Com_VideoDto video = dao.getVideo(num);
		//==========글작성자와 로그인한 아이디 체크 시작=========//
		if(!AuthUtil.getUser_id(request).equals(video.getId()))
			return "redirect:/com_video/detail.do?num="+num;
		//==========글작성자와 로그인한 아이디 체크 끝=========//
		dao.deleteVideo(video);
		return "/WEB-INF/views/community/video/delete.jsp";
	}
}
