package comment.service;

import java.util.ArrayList;

import comment.dao.CommentDAO;
import comment.vo.Comment;
import post.vo.Post;

public class CommentService {

	public ArrayList<Comment> loadAllComment(Post post) {	
		ArrayList<Comment> list = null;
		try {
			CommentDAO dao = new CommentDAO();
			list = dao.selectAll(post);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public int writeComment(Comment comment) {
		int result = 0;
		try {
			CommentDAO dao = new CommentDAO();
			result = dao.insert(comment);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
