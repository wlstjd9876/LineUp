package kr.community.video.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.community.video.dao.Com_VideoDao;
import kr.community.video.domain.Com_VideoDto;
import kr.controller.Action;
import kr.util.AuthUtil;
import kr.util.StringUtil;

public class ModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//

		int num = Integer.parseInt(request.getParameter("num"));

		Com_VideoDao dao = Com_VideoDao.getInstance();
		Com_VideoDto video = dao.getVideo(num);
		//==========���ۼ��ڿ� �α����� ���̵� üũ ����=========//
		if(!AuthUtil.isAuthWriter(request, video.getId()))
			return "redirect:/com_video/detail.do?num="+num;
		//==========���ۼ��ڿ� �α����� ���̵� üũ ��=========//
		video.setContent(StringUtil.useBrHtml(video.getContent()));
		request.setAttribute("video", video);

		return "/WEB-INF/views/community/video/modifyForm.jsp";
	}

}
