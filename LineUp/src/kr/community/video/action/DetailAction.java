package kr.community.video.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.community.video.dao.Com_VideoDao;
import kr.community.video.domain.Com_VideoDto;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		Com_VideoDao dao = Com_VideoDao.getInstance();
		Com_VideoDto video = dao.getVideo(num);
		dao.updateReadcount(num);
		
		//¡Ÿ πŸ≤ﬁ √≥∏Æ
		video.setContent(StringUtil.useBrHtml(video.getContent()));
		
		request.setAttribute("video", video);
		
		return "/WEB-INF/views/community/video/detail.jsp";
	}

}
