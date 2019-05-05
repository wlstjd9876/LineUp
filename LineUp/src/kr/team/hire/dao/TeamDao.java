package kr.team.hire.dao;

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

import kr.location.domain.LocationDto;
import kr.messenger.dao.MessengerDao;
import kr.messenger.domain.MessengerDto;
import kr.team.hire.domain.TeamDto;
import kr.teamapp.dao.TeamAppDao;

public class TeamDao {
	//싱글턴 패턴
	private static TeamDao instance = new TeamDao();
	public static TeamDao getInstance(){
		return instance;
	}
	private TeamDao(){}

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
	//글 등록
	public void insertTeamLineup(TeamDto team)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO team_lineup_hire VALUES (team_lineup_hire_seq.nextval, ?, ?, ?, to_date(?,'YY-MM-DD HH24:mi'), to_date(?,'YY-MM-DD HH24:mi'), ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, team.getTeam_id());
			pstmt.setInt(2, team.getTeam_loc_num());
			pstmt.setInt(3, team.getTeam_member());
			pstmt.setString(4, team.getTeam_date());
			pstmt.setString(5, team.getTeam_end_date());
			pstmt.setString(6, team.getTeam_age());
			pstmt.setInt(7, team.getTeam_money());
			pstmt.setString(8, team.getTeam_etc());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//전체 글 개수
	public int getTeamCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM team_lineup_hire";
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
	public TeamDto getApplyForm(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeamDto team = null;
		TeamAppDao dao = TeamAppDao.getInstance();
		LocationDto loc = null;
		SimpleDateFormat dt = new SimpleDateFormat("yy년 MM월 dd일 a hh : mm");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sql = null;

		try {
			conn = getConnection();
			sql = "SELECT * FROM team_lineup_hire h LEFT JOIN location loc ON(h.team_loc_num = loc.loc_num) WHERE h.team_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				team = new TeamDto();
				loc = new LocationDto();
				team.setTeam_num(rs.getInt("team_num"));
				team.setTeam_id(rs.getString("team_id"));
				team.setTeam_loc_num(rs.getInt("team_loc_num"));
				team.setTeam_member(rs.getInt("team_member"));
				team.setTeam_date(dt.format(rs.getTimestamp("team_date")));
				team.setTeam_end_date(dt.format(rs.getTimestamp("team_end_date")));
				team.setTeam_age(rs.getString("team_age"));
				team.setTeam_money(rs.getInt("team_money"));
				team.setTeam_etc(rs.getString("team_etc"));
				team.setTeam_now_member(dao.getTeamAppCount(team.getTeam_num()));
				loc.setLoc_name(rs.getString("loc_name"));
				loc.setLoc_address(rs.getString("loc_address"));
				loc.setLoc_content(rs.getString("loc_content"));
				team.setLoc(loc);
				team.setEnd_date(dt2.format(rs.getTimestamp("team_end_date")).split(" "));
				team.setDate(dt2.format(rs.getTimestamp("team_date")).split(" "));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return team;
	}


	//경기 목록
	public List<TeamDto> getTeamList(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TeamDto> list = null;
		SimpleDateFormat dt = new SimpleDateFormat("YY년 MM월 dd일 a hh : mm");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		TeamAppDao dao = TeamAppDao.getInstance();
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM team_lineup_hire t LEFT JOIN location loc ON(t.team_loc_num = loc.loc_num) ORDER BY team_num DESC)a) WHERE"
					+ " rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			list = new ArrayList<TeamDto>();
			while(rs.next()) {
				TeamDto team = new TeamDto();
				LocationDto loc = new LocationDto();
				team.setTeam_num(rs.getInt("team_num"));
				team.setTeam_id(rs.getString("team_id"));
				team.setTeam_loc_num(rs.getInt("team_loc_num"));
				team.setTeam_member(rs.getInt("team_member"));
				team.setTeam_date(dt.format(rs.getTimestamp("team_date")));
				team.setTeam_end_date(dt.format(rs.getTimestamp("team_end_date")));
				team.setTeam_age(rs.getString("team_age"));
				team.setTeam_money(rs.getInt("team_money"));
				team.setTeam_etc(rs.getString("team_etc"));
				team.setTeam_now_member(dao.getTeamAppCount(team.getTeam_num()));
				loc.setLoc_name(rs.getString("loc_name"));
				loc.setLoc_address(rs.getString("loc_address"));
				loc.setLoc_content(rs.getString("loc_content"));
				team.setLoc(loc);
				team.setEnd_date(dt2.format(rs.getTimestamp("team_end_date")).split(" "));
				team.setDate(dt2.format(rs.getTimestamp("team_date")).split(" "));
				list.add(team);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//경기 정보 수정
	public void modifyTeamLineup(TeamDto team)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE team_lineup_hire SET team_loc_num=?, team_member=?, team_date=to_date(?,'YY-MM-DD HH24:mi'), team_end_date=to_date(?,'YY-MM-DD HH24:mi'), team_age=?, team_money=?, team_etc=? WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team.getTeam_loc_num());
			pstmt.setInt(2, team.getTeam_member());
			pstmt.setString(3, team.getTeam_date());
			pstmt.setString(4, team.getTeam_end_date());
			pstmt.setString(5, team.getTeam_age());
			pstmt.setInt(6, team.getTeam_money());
			pstmt.setString(7, team.getTeam_etc());
			pstmt.setInt(8, team.getTeam_num());
			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//경기 삭제
	public void deleteTeamLineup(int team_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			sql = "DELETE FROM team_lineup_hire_app WHERE app_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			pstmt.executeUpdate();

			sql = "DELETE FROM team_lineup_hire WHERE team_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, team_num);
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
		ResultSet rs = null;
		String sql = null;
		MessengerDto dto = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT * FROM team_lineup_hire t LEFT JOIN location loc ON(t.team_loc_num = loc.loc_num) LEFT JOIN team_lineup_hire_app app ON(t.team_num = app.app_num) WHERE t.team_loc_num = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, loc_num);
			rs = pstmt3.executeQuery();

			while(rs.next()) {
				MessengerDao dao = MessengerDao.getInstance();
				dto = new MessengerDto();
				sql = "DELETE FROM team_lineup_hire_app WHERE app_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("team_num"));
				if(rs.getInt("app_num")!=0) {
					dto.setTitle(rs.getString("loc_name")+"경기장이 삭제되었습니다.");
					dto.setContent(rs.getString("loc_name")+"경기장 삭제로 인해 \n"+rs.getString("team_date").split(" ")[0]+"날짜의 개인라인업 경기가 취소되었습니다.");
					dto.setSender_id(rs.getString("team_id"));
					dto.setReceiver_id(rs.getString("app_id"));
					dao.sendMessage(dto);
				}
				pstmt.executeUpdate();
			}

			sql = "DELETE FROM team_lineup_hire WHERE team_loc_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, loc_num);
			pstmt2.executeUpdate();

			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt2, null);
			executeClose(null, pstmt, null);
			executeClose(rs, pstmt3, conn);
		}

	}

}