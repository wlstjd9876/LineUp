package kr.member.dao;

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
import kr.member.domain.T_MemberDto;
import kr.team.hire.domain.TeamDto;
import kr.teamapp.dao.TeamAppDao;

public class T_MemberDao {
	//싱글턴 패턴
	private static T_MemberDao instance = new T_MemberDao();
	public static T_MemberDao getInstance(){
		return instance;
	}
	private T_MemberDao(){}

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
	//회원 가입
	public void insertMember(T_MemberDto member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			sql = "INSERT INTO member (id,auth) VALUES (?,2)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.executeUpdate();

			sql = "INSERT INTO member_team (id,pw,phone,email,team_name,team_age,auth) VALUES "
					+ "(?,?,?,?,?,?,2)";
			pstmt2 = conn.prepareStatement(sql);

			pstmt2.setString(1, member.getId());
			pstmt2.setString(2, member.getPw());
			pstmt2.setString(3, member.getPhone());
			pstmt2.setString(4, member.getEmail());
			pstmt2.setString(5, member.getTeam_name());
			pstmt2.setString(6, member.getTeam_age());
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

	//닉네임,폰번호, 이메일 찾기
	public boolean checkDuplicated(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		String sql = null;

		try {
			conn = getConnection();

			if(keyfield.equals("phone")) keyfield = "phone";
			else if(keyfield.equals("email")) keyfield = "email";
			else keyfield = "nick";

			sql = "SELECT * FROM member_team WHERE " + keyfield + " = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);

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

	//회원 정보 수정
	public void modifyMember(T_MemberDto member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE member_team SET phone=?, email=?, team_name=?, team_age=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPhone());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getTeam_name());
			pstmt.setString(4, member.getTeam_age());;
			pstmt.setString(5, member.getId());

			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//회원 상세 정보
	public T_MemberDto getMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		T_MemberDto member = null;
		String sql = null;

		try {
			conn = getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member m LEFT OUTER JOIN "
					+ "member_team tm ON m.id = tm.id "
					+ "WHERE m.id=? AND  m.auth!=1 AND m.auth!=3";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				member = new T_MemberDto();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setTeam_name(rs.getString("team_name"));
				member.setTeam_age(rs.getString("team_age"));
				member.setAuth(rs.getInt("auth"));
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}

		return member;
	}
	public int getTeamCount(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM team_lineup_hire WHERE team_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

	//작성글 확인
	public List<TeamDto> getWriteList(int start, int end, String id)throws Exception{
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

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM team_lineup_hire t LEFT JOIN location loc ON(t.team_loc_num = loc.loc_num) WHERE team_id=? ORDER BY team_num DESC)a) WHERE"
					+ " rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
	public void deleteMember(String id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			sql = "UPDATE member SET auth=0 WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

			sql = "DELETE FROM 	member_team WHERE id=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1, id);
			pstmt2.executeUpdate();

			conn.commit();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, null);
			executeClose(null, pstmt2, conn);
		}

	}
}
