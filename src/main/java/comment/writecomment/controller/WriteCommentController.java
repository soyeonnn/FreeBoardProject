package comment.writecomment.controller;

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
 * Servlet implementation class WriteCommentController
 */
@WebServlet("/writecomment")
public class WriteCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		String uid = user.getUid();
		int pid = Integer.getInteger(request.getParameter("pid"));
		String ccontent = request.getParameter("ccontent");
		
		Comment comment = new Comment();
		//comment.setPid(pid);
		comment.setCwriter(uid);
		comment.setCcontent(ccontent);

		CommentService service = new CommentService();
		int result = service.writeComment(comment);
		
		if(result == 1) {
			PostService pservice = new PostService();
			Post post = new Post();
			post.setPcomment(pid);
			post = pservice.loadDetailPost(post);
			
			ArrayList<Comment> comments = service.loadAllComment(post);
			
			RequestDispatcher rd = request.getRequestDispatcher("/viewpost/viewpost.jsp");
			session.setAttribute("user", user);
			request.setAttribute("post", post);
			request.setAttribute("comment", comments);
			rd.forward(request, response);
		} else {
			response.sendRedirect("/freeboard/writepost/writepost.jsp");
		}
	}
}