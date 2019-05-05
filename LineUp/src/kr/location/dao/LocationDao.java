package kr.location.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.location.domain.LocationDto;
import kr.util.FileUtil;

public class LocationDao {
	//싱글턴 패턴
	private static LocationDao instance = new LocationDao();
	public static LocationDao getInstance(){
		return instance;
	}
	private LocationDao(){}

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
	//경기장등록
	public void insertLocation(LocationDto loc)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO location (loc_num, loc_name, loc_address, loc_phone, loc_photo1, loc_photo2, loc_content, loc_id)"
					+ " VALUES (location_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc.getLoc_name());
			pstmt.setString(2, loc.getLoc_address());
			pstmt.setString(3, loc.getLoc_phone());
			pstmt.setString(4, loc.getLoc_photo1());
			pstmt.setString(5, loc.getLoc_photo2());
			pstmt.setString(6, loc.getLoc_content());
			pstmt.setString(7, loc.getLoc_id());
			pstmt.executeUpdate();
		}catch(Exception e) {
			//오류 발생시 업로드된 파일 제거
			if(loc.getLoc_photo1()!=null) {
				FileUtil.removeFile(loc.getLoc_photo1());
				FileUtil.removeFile(loc.getLoc_photo2());
			}
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//전체 경기장 개수/경기장 개수
	public int getLocationCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			if(keyword == null || "".equals(keyword)) {
				sql = "SELECT COUNT(*) FROM location";
				pstmt = conn.prepareStatement(sql);
			}else {
				//검색글 개수
				if(keyfield.equals("loc_name")) keyfield = "loc_name";
				else keyfield = "loc_address";
				sql = "SELECT COUNT(*) FROM location WHERE " + keyfield + " LIKE ?";
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

	//경기장 목록/검색 경기장 목록
	public List<LocationDto> getLocationList(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LocationDto> list = null;
		String sql = null;

		try {
			conn = getConnection();

			if(keyword==null || "".equals(keyword)) {
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM location ORDER BY loc_num DESC)a) WHERE"
						+ " rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				if(keyfield.equals("loc_name")) keyfield = "loc_name";
				else keyfield = "loc_address";
				//전체 상품 보기
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM location WHERE "+keyfield+" LIKE ? ORDER BY loc_num DESC)a) WHERE"
						+ " rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			rs = pstmt.executeQuery();

			list = new ArrayList<LocationDto>();
			while(rs.next()) {
				LocationDto loc = new LocationDto();

				loc.setLoc_name(rs.getString("loc_name"));
				loc.setLoc_address(rs.getString("loc_address"));
				loc.setLoc_phone(rs.getString("loc_phone"));
				loc.setLoc_photo1(rs.getString("loc_photo1"));
				loc.setLoc_photo2(rs.getString("loc_photo2"));
				loc.setLoc_content(rs.getString("loc_content"));
				loc.setLoc_num(rs.getInt("loc_num"));
				loc.setLoc_id(rs.getString("loc_id"));

				list.add(loc);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//주소 검색
	public boolean checkDuplicated(String loc_address) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM location WHERE loc_address = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_address);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				check = true;
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return check;
	}

	//경기장 상세 정보
	public LocationDto getLocation(int loc_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocationDto loc = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM location WHERE loc_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loc_num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				loc = new LocationDto();

				loc.setLoc_num(rs.getInt("loc_num"));
				loc.setLoc_name(rs.getString("loc_name"));
				loc.setLoc_address(rs.getString("loc_address"));
				loc.setLoc_phone(rs.getString("loc_phone"));
				loc.setLoc_photo1(rs.getString("loc_photo1"));
				loc.setLoc_photo2(rs.getString("loc_photo2"));
				loc.setLoc_content(rs.getString("loc_content"));
				loc.setLoc_id(rs.getString("loc_id"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return loc;
	}

	//경기장 정보 수정
	public void modifyLocation(LocationDto loc)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE location SET loc_name=?, loc_address=?, loc_phone=?, loc_photo1=?, loc_photo2=?, loc_content=?, loc_id=? WHERE loc_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc.getLoc_name());
			pstmt.setString(2, loc.getLoc_address());
			pstmt.setString(3, loc.getLoc_phone());
			pstmt.setString(4, loc.getLoc_photo1());
			pstmt.setString(5, loc.getLoc_photo2());
			pstmt.setString(6, loc.getLoc_content());
			pstmt.setString(7, loc.getLoc_id());
			pstmt.setInt(8, loc.getLoc_num());
			pstmt.executeUpdate();

		}catch (Exception e) {
			//오류 발생시 업로드된 파일 제거
			if(loc.getLoc_photo1()!=null) {
				FileUtil.removeFile(loc.getLoc_photo1());
				FileUtil.removeFile(loc.getLoc_photo2());
			}
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//경기장 삭제
	public void deleteLocation(LocationDto loc)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			
			sql = "DELETE FROM location WHERE loc_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loc.getLoc_num());
			pstmt.executeUpdate();
			if(!loc.getLoc_photo1().equals("no_detail_img.jpg")) {
				FileUtil.removeFile(loc.getLoc_photo1());
				FileUtil.removeFile(loc.getLoc_photo2());
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}

	}
}