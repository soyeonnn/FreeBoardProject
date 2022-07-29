package post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import post.vo.Post;

public class PostDAO {

	private DataSource ds;
	
	public PostDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/MySQLDB");
	}
	
	public ArrayList<Post> selectAll() throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "SELECT * FROM post";
		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Post> list = new ArrayList<Post>();
		while(rs.next() ) {
			Post post = new Post();
			post.setPid(rs.getInt("pid"));
			post.setPtitle(rs.getString("ptitle"));
			post.setPwriter(rs.getString("pwriter"));
			post.setPcontent(rs.getString("pcontent"));
			post.setPdate(rs.getString("pdate"));
			post.setPlike(rs.getInt("plike"));
			post.setPview(rs.getInt("pview"));
			post.setPcomment(rs.getInt("pcomment"));

			list.add(post);
		}
		return list;
	}
	
	public int insertPost(Post post) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "INSERT INTO post(ptitle, pwriter, pcontent, pdate, plike, pview, pcomment) VALUES(?, ?, ?, NOW(), 0, 0, 0)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, post.getPtitle());
		pstmt.setString(2, post.getPwriter());
		pstmt.setString(3, post.getPcontent());

		int result = pstmt.executeUpdate();

		return result;
	}
	
	public Post selectpost(Post post) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "SELECT ptitle, pwriter, pcontent, pdate, plike, pview, pcomment FROM post WHERE pid = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, post.getPid());

		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next() ) {
			post.setPtitle(rs.getString("ptitle"));
			post.setPwriter(rs.getString("pwriter"));
			post.setPcontent(rs.getString("pcontent"));
			post.setPdate(rs.getString("pdate"));
			post.setPlike(rs.getInt("plike"));
			post.setPview(rs.getInt("pview"));
			post.setPcomment(rs.getInt("pcomment"));
		}
		return post;
	}
	
	public int modifyPost(Post post) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "UPDATE post SET ptitle = ?, pcontent = ? WHERE pid = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, post.getPtitle());
		pstmt.setString(2, post.getPcontent());
		pstmt.setInt(3, post.getPid());

		int result = pstmt.executeUpdate();

		return result;
	}
	
	public int deletePost(int delpid) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "DELETE FROM post WHERE pid = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, delpid);

		int result = pstmt.executeUpdate();

		return result;
	}
	
	public int updateViewCount(Post post) throws Exception {
		Connection con = ds.getConnection();
		System.out.println("connection 획득 성공!");
		
		String sql = "UPDATE post SET pview = ? WHERE pid = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, post.getPview() + 1);
		pstmt.setInt(2, post.getPid());

		int result = pstmt.executeUpdate();

		return result;
	}
}
