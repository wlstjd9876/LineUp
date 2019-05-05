package kr.community.video.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.community.video.domain.Com_VideoDto;
import kr.location.domain.LocationDto;
import kr.util.FileUtil;

public class Com_VideoDao {
	//싱글턴 패턴
	private static Com_VideoDao instance = new Com_VideoDao();
	public static Com_VideoDao getInstance(){
		return instance;
	}
	private Com_VideoDao(){}

	//커넥션 풀로부터 커넥션을 할당
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");

		return ds.getConnection();
	}
	//자원정리
	private void executeClose(ResultSet rs,
			PreparedStatement pstmt,Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	//조회수 증가
	public void updateReadcount(int num)
			throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE com_video SET hit=hit+1 WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//영상 등록
	public void insertVideo(Com_VideoDto video)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO com_video (num, title, content, ip, id, thumb)"
					+ " VALUES (com_video_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, video.getTitle());
			pstmt.setString(2, video.getContent());
			pstmt.setString(3, video.getIp());
			pstmt.setString(4, video.getId());
			pstmt.setString(5, video.getThumb());
			pstmt.executeUpdate();
		}catch(Exception e) {
			//오류 발생시 업로드된 파일 제거
			if(video.getThumb()!=null) {
				FileUtil.removeFile(video.getThumb());
			}
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//전체 영상 개수/영상 개수
	public int getVideoCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			if(keyword == null || "".equals(keyword)) {
				sql = "SELECT COUNT(*) FROM com_video";
				pstmt = conn.prepareStatement(sql);
			}else {
				//검색글 개수
				if(keyfield.equals("title")) keyfield = "title";
				else keyfield = "content";
				sql = "SELECT COUNT(*) FROM com_video WHERE " + keyfield + " LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
			}
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return count;
	}

	//영상 목록/검색 영상 목록
	public List<Com_VideoDto> getVideoList(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Com_VideoDto> list = null;
		String sql = null;

		try {
			conn = getConnection();

			if(keyword==null || "".equals(keyword)) {
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM com_video ORDER BY reg_date DESC)a) WHERE"
						+ " rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				if(keyfield.equals("title")) keyfield = "title";
				else keyfield = "content";
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM com_video WHERE "+keyfield+" LIKE ? ORDER BY reg_date DESC)a) WHERE"
						+ " rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			rs = pstmt.executeQuery();

			list = new ArrayList<Com_VideoDto>();
			while(rs.next()) {
				Com_VideoDto video = new Com_VideoDto();

				video.setNum(rs.getInt("num"));
				video.setTitle(rs.getString("title"));
				video.setContent(rs.getString("content"));
				video.setHit(rs.getInt("hit"));
				video.setReg_date(rs.getDate("reg_date"));
				video.setModify_date(rs.getDate("modify_date"));
				video.setIp(rs.getString("ip"));
				video.setId(rs.getString("id"));
				video.setThumb(rs.getString("thumb"));

				list.add(video);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//영상 상세정보
	public Com_VideoDto getVideo(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Com_VideoDto video = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM Com_video WHERE num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				video = new Com_VideoDto();

				video.setNum(rs.getInt("num"));
				video.setTitle(rs.getString("title"));
				video.setContent(rs.getString("content"));
				video.setReg_date(rs.getDate("reg_date"));
				video.setModify_date(rs.getDate("modify_date"));
				video.setIp(rs.getString("ip"));
				video.setId(rs.getString("id"));
				video.setThumb(rs.getString("thumb"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return video;
	}

	//영상 수정
	public void modifyLocation(Com_VideoDto video)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE com_video SET title=?, content=?, modify_date=sysdate, ip=?, id=?, thumb=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, video.getTitle());
			pstmt.setString(2, video.getContent());
			pstmt.setString(3, video.getIp());
			pstmt.setString(4, video.getId());
			pstmt.setString(5, video.getThumb());
			pstmt.setInt(6, video.getNum());
			pstmt.executeUpdate();

		}catch (Exception e) {
			//오류 발생시 업로드된 파일 제거
			if(video.getThumb()!=null) {
				FileUtil.removeFile(video.getThumb());
			}
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//영상 삭제
	public void deleteVideo(Com_VideoDto video)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();

			sql = "DELETE FROM com_video WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, video.getNum());
			pstmt.executeUpdate();
			FileUtil.removeFile(video.getThumb());
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}

	}
}