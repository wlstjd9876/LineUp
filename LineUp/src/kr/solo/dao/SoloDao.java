package kr.solo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.location.dao.LocationDao;
import kr.location.domain.LocationDto;
import kr.messenger.dao.MessengerDao;
import kr.messenger.domain.MessengerDto;
import kr.solo.domain.SoloDto;
import kr.solo.domain.SoloTempDto;
import kr.soloapp.dao.SoloAppDao;

public class SoloDao {
	//싱글턴 패턴
	private static SoloDao instance = new SoloDao();
	public static SoloDao getInstance(){
		return instance;
	}
	private SoloDao(){}

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
	//경기 등록
	public void insertSoloLineup(SoloDto solo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO solo_lineup VALUES (solo_lineup_seq.nextval, ?, ?, to_date(?,'YY-MM-DD HH24:mi'), to_date(?,'YY-MM-DD HH24:mi'), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, solo.getSolo_min_member());
			pstmt.setInt(2, solo.getSolo_member());
			pstmt.setString(3, solo.getSolo_end_date());
			pstmt.setString(4, solo.getSolo_date());
			pstmt.setInt(5, solo.getSolo_loc_num());
			pstmt.setString(6, solo.getSolo_id());
			pstmt.setInt(7, solo.getSolo_money());
			pstmt.setString(8, solo.getSolo_age());
			pstmt.setString(9, solo.getSolo_gen());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//임시 저장 글 불러오기
	public SoloTempDto checkSoloLineupTemp(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		LocationDto loc = null;
		SoloTempDto solo = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM solo_lineup_temp WHERE solo_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				solo = new SoloTempDto();
				LocationDao dao = LocationDao.getInstance();
				loc = new LocationDto();
				solo.setSolo_min_member(rs.getString("solo_min_member"));
				solo.setSolo_member(rs.getString("solo_member"));
				solo.setSolo_end_date(rs.getString("solo_end_date"));
				solo.setSolo_date(rs.getString("solo_date"));
				solo.setSolo_loc_num(rs.getInt("solo_loc_num"));
				solo.setSolo_id(rs.getString("solo_id"));
				solo.setSolo_money(rs.getString("solo_money"));
				solo.setSolo_age(rs.getString("solo_age"));
				solo.setSolo_gen(rs.getString("solo_gen"));
				if(rs.getInt("solo_loc_num")!=0)
					loc.setLoc_name(dao.getLocation(rs.getInt("solo_loc_num")).getLoc_name());
				solo.setLoc(loc);
				solo.setEnd_date(rs.getString("solo_end_date").split(" "));
				solo.setDate(rs.getString("solo_date").split(" "));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return solo;
	}

	//임시 등록
	public void insertSoloLineupTemp(SoloTempDto solo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO solo_lineup_temp VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, solo.getSolo_min_member());
			pstmt.setString(2, solo.getSolo_member());
			pstmt.setString(3, solo.getSolo_end_date());
			pstmt.setString(4, solo.getSolo_date());
			pstmt.setInt(5, solo.getSolo_loc_num());
			pstmt.setString(6, solo.getSolo_id());
			pstmt.setString(7, solo.getSolo_money());
			pstmt.setString(8, solo.getSolo_age());
			pstmt.setString(9, solo.getSolo_gen());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//임시 등록 글 삭제
	public void deleteSoloLineupTemp(String id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();

			sql = "DELETE FROM solo_lineup_temp WHERE solo_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}

	}

	//전체 경기 개수
	public int getSoloCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM solo_lineup";
			pstmt = conn.prepareStatement(sql);
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
	//경기 상세 정보 가져오기
	public SoloDto getApplyForm(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SoloAppDao dao =SoloAppDao.getInstance();
		SoloDto solo = null;
		LocationDto loc = null;
		SimpleDateFormat dt = new SimpleDateFormat("yy년 MM월 dd일 a hh : mm");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM solo_lineup s LEFT JOIN location loc ON(s.solo_loc_num = loc.loc_num) WHERE s.solo_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				solo = new SoloDto();
				loc = new LocationDto();
				solo.setSolo_num(rs.getInt("solo_num"));
				solo.setSolo_min_member(rs.getInt("solo_min_member"));
				solo.setSolo_member(rs.getInt("solo_member"));
				solo.setSolo_end_date(dt.format(rs.getTimestamp("solo_end_date")));
				solo.setSolo_date(dt.format(rs.getTimestamp("solo_date")));
				solo.setSolo_loc_num(rs.getInt("solo_loc_num"));
				solo.setSolo_id(rs.getString("solo_id"));
				solo.setSolo_money(rs.getInt("solo_money"));
				solo.setSolo_age(rs.getString("solo_age"));
				solo.setSolo_gen(rs.getString("solo_gen"));
				loc.setLoc_name(rs.getString("loc_name"));
				loc.setLoc_address(rs.getString("loc_address"));
				loc.setLoc_content(rs.getString("loc_content"));
				solo.setSolo_now_member(dao.getSoloAppCount(solo.getSolo_num()));
				solo.setLoc(loc);
				solo.setEnd_date(dt2.format(rs.getTimestamp("solo_end_date")).split(" "));
				solo.setDate(dt2.format(rs.getTimestamp("solo_date")).split(" "));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}

		return solo;
	}

	//경기 목록
	public List<SoloDto> getSoloList(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SoloDto> list = null;
		SoloAppDao dao =SoloAppDao.getInstance();
		SimpleDateFormat dt = new SimpleDateFormat("YY년 MM월 dd일 a hh : mm");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM solo_lineup s LEFT JOIN location loc ON(s.solo_loc_num = loc.loc_num) ORDER BY solo_num DESC)a) WHERE"
					+ " rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			list = new ArrayList<SoloDto>();
			while(rs.next()) {
				SoloDto solo = new SoloDto();
				LocationDto loc = new LocationDto();
				solo.setSolo_num(rs.getInt("solo_num"));
				solo.setSolo_min_member(rs.getInt("solo_min_member"));
				solo.setSolo_member(rs.getInt("solo_member"));
				solo.setSolo_end_date(dt.format(rs.getTimestamp("solo_end_date")));
				solo.setSolo_date(dt.format(rs.getTimestamp("solo_date")));
				solo.setSolo_loc_num(rs.getInt("solo_loc_num"));
				solo.setSolo_id(rs.getString("solo_id"));
				solo.setSolo_money(rs.getInt("solo_money"));
				solo.setSolo_age(rs.getString("solo_age"));
				solo.setSolo_gen(rs.getString("solo_gen"));
				loc.setLoc_name(rs.getString("loc_name"));
				loc.setLoc_address(rs.getString("loc_address"));
				loc.setLoc_content(rs.getString("loc_content"));
				solo.setSolo_now_member(dao.getSoloAppCount(solo.getSolo_num()));
				solo.setLoc(loc);
				solo.setEnd_date(dt2.format(rs.getTimestamp("solo_end_date")).split(" "));
				solo.setDate(dt2.format(rs.getTimestamp("solo_date")).split(" "));
				list.add(solo);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//경기 정보 수정
	public void modifySoloLineup(SoloDto solo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE solo_lineup SET solo_min_member=?, solo_member=?, solo_end_date=to_date(?,'YY-MM-DD HH24:mi'), solo_date=to_date(?,'YY-MM-DD HH24:mi'), solo_loc_num=?, solo_id=?, solo_money=?, solo_age=?, solo_gen=? WHERE solo_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, solo.getSolo_min_member());
			pstmt.setInt(2, solo.getSolo_member());
			pstmt.setString(3, solo.getSolo_end_date());
			pstmt.setString(4, solo.getSolo_date());
			pstmt.setInt(5, solo.getSolo_loc_num());
			pstmt.setString(6, solo.getSolo_id());
			pstmt.setInt(7, solo.getSolo_money());
			pstmt.setString(8, solo.getSolo_age());
			pstmt.setString(9, solo.getSolo_gen());
			pstmt.setInt(10, solo.getSolo_num());
			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//경기 삭제
	public void deleteSoloLineup(int solo_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			sql = "DELETE FROM solo_lineup_app WHERE app_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, solo_num);
			pstmt.executeUpdate();

			sql = "DELETE FROM solo_lineup WHERE solo_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, solo_num);
			pstmt2.executeUpdate();
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt2, null);
			executeClose(null, pstmt, conn);
		}

	}
	//경기 삭제
	public void deleteLocation(int loc_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		String sql = null;
		MessengerDto dto = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT * FROM solo_lineup s LEFT JOIN location loc ON(s.solo_loc_num = loc.loc_num) LEFT JOIN solo_lineup_app app ON(s.solo_num = app.app_num) WHERE s.solo_loc_num = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, loc_num);
			rs = pstmt3.executeQuery();

			while(rs.next()) {
				MessengerDao dao = MessengerDao.getInstance();
				dto = new MessengerDto();
				sql = "DELETE FROM solo_lineup_app WHERE app_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("solo_num"));
				if(rs.getInt("app_num")!=0) {
					dto.setTitle(rs.getString("loc_name")+"경기장이 삭제되었습니다.");
					dto.setContent(rs.getString("loc_name")+"경기장 삭제로 인해 \n"+rs.getString("solo_date").split(" ")[0]+"날짜의 개인라인업 경기가 취소되었습니다.");
					dto.setSender_id(rs.getString("solo_id"));
					dto.setReceiver_id(rs.getString("app_id"));
					dao.sendMessage(dto);
				}
				pstmt.executeUpdate();
			}

			sql = "DELETE FROM solo_lineup WHERE solo_loc_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, loc_num);
			pstmt2.executeUpdate();
			
			sql = "UPDATE solo_lineup_temp SET solo_loc_num=null WHERE solo_loc_num=?";
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setInt(1, loc_num);
			pstmt4.executeUpdate();
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt4, null);
			executeClose(null, pstmt2, null);
			executeClose(null, pstmt, null);
			executeClose(rs, pstmt3, conn);
		}

	}

}