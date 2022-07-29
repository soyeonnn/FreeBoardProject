package post.viewpost.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.service.CommentService;
import comment.vo.Comment;
import post.service.PostService;
import post.vo.Post;
import user.vo.User;

/**
 * Servlet implementation class ViewPostController
 */
@WebServlet("/viewpost")
public class ViewPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPostController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		Post post = new Post();
		post.setPid(pid);

		PostService service = new PostService();
		post = service.loadDetailPost(post);
		service.upViewCount(post, user);

		if(post != null) {			
			CommentService cservice = new CommentService();
			ArrayList<Comment> comment = cservice.loadAllComment(post);
			
			RequestDispatcher rd = request.getRequestDispatcher("/viewpost/viewpost.jsp");
			session.setAttribute("user", user);
			request.setAttribute("post", post);
			request.setAttribute("comment", comment);
			rd.forward(request, response);
		} else {
			response.sendRedirect("/freeboard/main/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
