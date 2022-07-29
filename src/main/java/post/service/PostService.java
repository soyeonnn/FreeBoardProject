package post.service;

import java.util.ArrayList;

import post.dao.PostDAO;
import post.vo.Post;
import user.vo.User;


public class PostService {

	public ArrayList<Post> loadAllPost() {	
		ArrayList<Post> list = null;
		try {
			PostDAO dao = new PostDAO();
			list = dao.selectAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public int uploadNewPost(Post post) {
		int result = 0;
		try {
			PostDAO dao = new PostDAO();
			result = dao.insertPost(post);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public Post loadDetailPost(Post post) {
		try {
			PostDAO dao = new PostDAO();
			post = dao.selectpost(post);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return post;
	}
	
	public int modifyPostContent(Post post) {
		int result = 0;
		try {
			PostDAO dao = new PostDAO();
			result = dao.modifyPost(post);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int delPost(int delpid) {
		int result = 0;
		try {
			PostDAO dao = new PostDAO();
			result = dao.deletePost(delpid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int upViewCount(Post post, User user) {
		int result = 0;
		try {
			PostDAO dao = new PostDAO();
			if(user.getUid().equals(post.getPwriter())) {
				return result;
			} else {
				dao.updateViewCount(post);
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}