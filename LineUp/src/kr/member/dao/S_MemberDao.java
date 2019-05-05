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
import kr.member.domain.S_MemberDto;
import kr.solo.domain.SoloDto;
import kr.soloapp.dao.SoloAppDao;
import kr.team.hire.domain.TeamDto;
import kr.teamapp.dao.TeamAppDao;

public class S_MemberDao {
	//싱글턴 패턴
	private static S_MemberDao instance = new S_MemberDao();
	public static S_MemberDao getInstance(){
		return instance;
	}
	private S_MemberDao(){}

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
	public void insertMember(S_MemberDto member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			sql = "INSERT INTO member (id,auth) VALUES (?,1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.executeUpdate();

			sql = "INSERT INTO member_solo (id,pw,phone,email,name,gen,age,auth,nick) VALUES "
					+ "(?,?,?,?,?,?,?,1,?)";
			pstmt2 = conn.prepareStatement(sql);

			pstmt2.setString(1, member.getId());
			pstmt2.setString(2, member.getPw());
			pstmt2.setString(3, member.getPhone());
			pstmt2.setString(4, member.getEmail());
			pstmt2.setString(5, member.getName());
			pstmt2.setString(6, member.getGen());
			pstmt2.setString(7, member.getAge());
			pstmt2.setString(8, member.getNick());
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

			sql = "SELECT * FROM member_solo WHERE " + keyfield + " = ?";
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
	public void modifyMember(S_MemberDto member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE member_solo SET phone=?, email=?, name=?, gen=?, age=?, nick=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPhone());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGen());
			pstmt.setString(5, member.getAge());;
			pstmt.setString(6, member.getNick());
			pstmt.setString(7, member.getId());

			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}

	//회원 상세 정보
	public S_MemberDto getMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		S_MemberDto member = null;
		String sql = null;

		try {
			conn = getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member m LEFT OUTER JOIN "
					+ "member_solo sm ON m.id = sm.id "
					+ "WHERE m.id=? AND (m.auth=1 OR m.auth=3)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new S_MemberDto();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setGen(rs.getString("gen"));
				member.setAge(rs.getString("age"));
				member.setAuth(rs.getInt("auth"));
				member.setNick(rs.getString("nick"));
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}

		return member;
	}
	//개인라인업 개수 확인
	public int getSoloCount(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM solo_lineup WHERE solo_id=?";
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
	//개인라인업 신청 개수 확인
	public int getSoloappCount(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM solo_lineup_app WHERE app_id=?";
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
	//팀라인업 신청개수 확인
	public int getTeamappCount(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM team_lineup_hire_app WHERE app_id=?";
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
	public List<SoloDto> getWriteList(int start, int end, String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SoloDto> list = null;
		SimpleDateFormat dt = new SimpleDateFormat("YY년 MM월 dd일 a hh : mm");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SoloAppDao dao = SoloAppDao.getInstance();
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM solo_lineup s LEFT JOIN location loc ON(s.solo_loc_num = loc.loc_num) WHERE solo_id=? ORDER BY solo_num DESC)a) WHERE"
					+ " rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
	//신청글 확인(팀)
	public List<TeamDto> getTeamAppList(int start, int end, String id)throws Exception{
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

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM (SELECT * FROM team_lineup_hire t LEFT JOIN team_lineup_hire_app app ON(t.team_num = app.app_num) WHERE app_id=?)team LEFT JOIN location loc ON(team.team_loc_num = loc.loc_num))a) WHERE"
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

	//신청글 확인(개인)
	public List<SoloDto> getAppList(int start, int end, String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SoloDto> list = null;
		SimpleDateFormat dt = new SimpleDateFormat("YY년 MM월 dd일 a hh : mm");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SoloAppDao dao = SoloAppDao.getInstance();
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM (SELECT * FROM solo_lineup s LEFT JOIN solo_lineup_app app ON(s.solo_num = app.app_num) WHERE app_id=?)solo LEFT JOIN location loc ON(solo.solo_loc_num = loc.loc_num))a) WHERE"
					+ " rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
	//회원 삭제
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

			sql = "DELETE FROM 	member_solo WHERE id=?";
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
