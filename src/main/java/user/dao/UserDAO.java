package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import user.vo.User;

public class UserDAO {

	private DataSource ds;
	
	public UserDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/MySQLDB");
	}

	public int insert(User user) throws Exception{
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "INSERT INTO user VALUES(?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUid());
		pstmt.setString(2, user.getPw());
		pstmt.setString(3, user.getName());
		
		int result = pstmt.executeUpdate();

		return result;
	}

	public User select(User user) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "SELECT name FROM user WHERE uid = ? AND pw = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUid());
		pstmt.setString(2, user.getPw());
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			user.setName(rs.getString("name"));
			return user;
		}
		return null;
	}
}
