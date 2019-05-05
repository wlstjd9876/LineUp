package kr.messenger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.messenger.domain.MessengerDto;

public class MessengerDao {
	//싱글턴 패턴
	private static MessengerDao instance = new MessengerDao();
	public static MessengerDao getInstance(){
		return instance;
	}
	private MessengerDao(){}

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
	//메세지 전송
	public void sendMessage(MessengerDto messenger)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "INSERT INTO messenger (num, title, content, reg_date, sender_id, receiver_id) VALUES (messenger_seq.nextval, ?, ?,  sysdate, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, messenger.getTitle());
			pstmt.setString(2, messenger.getContent());
			pstmt.setString(3, messenger.getSender_id());
			pstmt.setString(4, messenger.getReceiver_id());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}


	//메세지 개수
	public int getSoloCount(String kinds, String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM messenger WHERE "+kinds+"= ?";
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
	//메세지 개수
	public int checkMessage(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = getConnection();

			sql = "SELECT COUNT(*) FROM messenger WHERE receiver_id= ? AND check_m=0";
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
	//메세지 정보
	public MessengerDto getMessage(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		MessengerDto messenger = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM messenger WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				messenger = new MessengerDto();
				messenger.setNum(rs.getInt("num"));
				messenger.setTitle(rs.getString("title"));
				messenger.setContent(rs.getString("content"));
				messenger.setReg_date(rs.getDate("reg_date"));
				messenger.setSender_id(rs.getString("sender_id"));
				messenger.setReceiver_id(rs.getString("receiver_id"));
				messenger.setCheck_m(rs.getInt("check_m"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return messenger;
	}

	//메세지 목록
	public List<MessengerDto> getMessengerList(int start, int end, String kinds, String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MessengerDto> list = null;
		String sql = null;

		try {
			conn = getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM messenger WHERE "+kinds+"=? ORDER BY reg_date DESC)a) WHERE"
					+ " rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			list = new ArrayList<MessengerDto>();
			while(rs.next()) {
				MessengerDto messenger = new MessengerDto();
				messenger.setNum(rs.getInt("num"));
				messenger.setTitle(rs.getString("title"));
				messenger.setContent(rs.getString("content"));
				messenger.setReg_date(rs.getDate("reg_date"));
				messenger.setSender_id(rs.getString("sender_id"));
				messenger.setReceiver_id(rs.getString("receiver_id"));
				messenger.setCheck_m(rs.getInt("check_m"));
				list.add(messenger);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//읽음 표시
	public void modifyCheck(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "UPDATE messenger SET check_m=1 WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	//메세지 삭제
	public void deleteMessage(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "DELETE FROM messenger WHERE num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}


}