package kr.soloapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.member.dao.S_MemberDao;
import kr.member.domain.S_MemberDto;
import kr.soloapp.domain.SoloAppDto;


public class SoloAppDao {
	//싱글턴 패턴
	private static SoloAppDao instance = new SoloAppDao();
	public static SoloAppDao getInstance(){
		return instance;
	}
	private SoloAppDao(){}

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
	//신청
	public void apply(SoloAppDto solo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO solo_lineup_app VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, solo.getApp_num());
			pstmt.setString(2, solo.getApp_id());
			pstmt.setInt(3, solo.getApp_member());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//신청여부
	public boolean CheckApp(String id, int app_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean check = false;

		try {
			conn = getConnection();
			sql = "SELECT * FROM solo_lineup_app WHERE app_id=? AND app_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, app_num);
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

	//신청자 명수 구하는 메서드
	public int getSoloAppCount(int app_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM solo_lineup_app WHERE app_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				count += rs.getInt("app_member");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
		return count;
	}
	//신청 취소
	public void deleteApp(String id, int app_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "DELETE FROM solo_lineup_app WHERE app_id=? AND app_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, app_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//신청건수
	public int getAppCount(int app_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();
			sql = "SELECT COUNT(*) FROM solo_lineup_app WHERE app_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
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
	//신청자 목록
	public List<SoloAppDto> getListApp(int start,int end,int app_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SoloAppDto> list = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM (SELECT a.*,rownum rnum "
					+ "FROM (SELECT * FROM solo_lineup_app WHERE app_num=?)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			list = new ArrayList<SoloAppDto>();
			while(rs.next()) {
				SoloAppDto app = new SoloAppDto();
				S_MemberDto solo = new S_MemberDto();
				S_MemberDao dao = S_MemberDao.getInstance();
				app.setApp_id(rs.getString("app_id"));
				app.setApp_member(rs.getInt("app_member"));
				app.setApp_num(rs.getInt("app_num"));
				solo = dao.getMember(app.getApp_id());
				app.setName(solo.getName());
				app.setPhone(solo.getPhone());
				app.setAge(solo.getAge());
				app.setGen(solo.getGen());
				list.add(app);
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
}
