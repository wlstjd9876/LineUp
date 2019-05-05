package kr.teamapp.dao;

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
import kr.messenger.dao.MessengerDao;
import kr.messenger.domain.MessengerDto;
import kr.teamapp.domain.TeamAppDto;


public class TeamAppDao {
	//싱글턴 패턴
	private static TeamAppDao instance = new TeamAppDao();
	public static TeamAppDao getInstance(){
		return instance;
	}
	private TeamAppDao(){}

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
	//최종선발
	public void sendFinalMemberMsg(int app_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessengerDto dto = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM team_lineup_hire hire LEFT JOIN team_lineup_hire_app app ON (app.app_num = hire.team_num) "
					+ "LEFT JOIN location loc ON (hire.team_loc_num = loc.loc_num) WHERE app.app_num = ? AND app.app_status = 2";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MessengerDao dao = MessengerDao.getInstance();
				dto = new MessengerDto();

				if(rs.getInt("team_num")!=0) {
					dto.setTitle(rs.getString("loc_name")+"에서 하는 경기에 선발되셨습니다.");
					dto.setContent("최종 라인업에 " + rs.getString("app_id") + "님께서 선발되셨습니다.");
					dto.setSender_id(rs.getString("team_id"));
					dto.setReceiver_id(rs.getString("app_id"));
					dao.sendMessage(dto);
				}
			}

		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
	}
	//라인업하기!
	public void TeamLineup(String id, int app_num, int status)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			if(status ==2) {
				sql = "UPDATE team_lineup_hire_app SET app_status=2 WHERE app_num=? AND app_status=1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, app_num);
			}else {
				if(status ==0)
					sql = "UPDATE team_lineup_hire_app SET app_status=1 WHERE app_id=? AND app_num=?";
				else
					sql = "UPDATE team_lineup_hire_app SET app_status=0 WHERE app_id=? AND app_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, app_num);
			}
			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//신청
	public void apply(TeamAppDto team)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO team_lineup_hire_app VALUES (?, ?, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, team.getApp_id());
			pstmt.setInt(2, team.getApp_num());

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
			sql = "SELECT * FROM team_lineup_hire_app WHERE app_id=? AND app_num=?";
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
	public int getTeamAppCount(int app_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM team_lineup_hire_app WHERE app_num = ? AND (app_status=1 OR app_status=2)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				count ++;
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

			sql = "DELETE FROM team_lineup_hire_app WHERE app_id=? AND app_num=?";
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
			sql = "SELECT COUNT(*) FROM team_lineup_hire_app WHERE app_num=?";
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
	public boolean checkStatus(int app_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean ck = true;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM team_lineup_hire_app WHERE app_status=2 AND app_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ck = false;
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return ck;
	}
	//신청자 목록
	public List<TeamAppDto> getListApp(int start,int end,int app_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TeamAppDto> list = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM (SELECT a.*,rownum rnum "
					+ "FROM (SELECT * FROM team_lineup_hire_app WHERE app_num=?)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			list = new ArrayList<TeamAppDto>();
			while(rs.next()) {
				TeamAppDto app = new TeamAppDto();
				S_MemberDto solo = new S_MemberDto();
				S_MemberDao dao = S_MemberDao.getInstance();
				app.setApp_id(rs.getString("app_id"));
				app.setApp_status(rs.getInt("app_status"));
				app.setApp_num(rs.getInt("app_num"));
				solo = dao.getMember(app.getApp_id());
				app.setTeam_now_member(getTeamAppCount(rs.getInt("app_num")));
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
