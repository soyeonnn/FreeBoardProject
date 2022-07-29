package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import comment.vo.Comment;
import post.vo.Post;

public class CommentDAO {
	private DataSource ds;
	
	public CommentDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/MySQLDB");
	}

	public ArrayList<Comment> selectAll(Post post) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "SELECT cid, cwriter, ccontent FROM comment WHERE pid = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, post.getPid());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Comment> list = new ArrayList<Comment>();
		while(rs.next()) {
			Comment comment = new Comment();
			comment.setCid(rs.getInt("cid"));
			comment.setPid(post.getPid());
			comment.setCwriter(rs.getString("cwriter"));
			comment.setCcontent(rs.getString("ccontent"));
			list.add(comment);
		}
		return list;
	}
	
	public int insert(Comment comment) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "INSERT INTO comment(pid, cwriter, ccontent) VALUES(?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, comment.getPid());
		pstmt.setString(2, comment.getCwriter());
		pstmt.setString(3, comment.getCcontent());

		int result = pstmt.executeUpdate();

		return result;
	}
}
