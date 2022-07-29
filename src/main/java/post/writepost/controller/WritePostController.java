package post.writepost.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import post.service.PostService;
import post.vo.Post;
import user.vo.User;

/**
 * Servlet implementation class WritePostController
 */
@WebServlet("/writepost")
public class WritePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WritePostController() {
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
		String uid = request.getParameter("uid");
		String ptitle = request.getParameter("ptitle");
		String pcontent = request.getParameter("pcontent");
		
		Post post = new Post();
		post.setPwriter(uid);
		post.setPtitle(ptitle);
		post.setPcontent(pcontent);

		PostService service = new PostService();
		int result = service.uploadNewPost(post);

		if(result == 1) {
			PostService pservice = new PostService();
			ArrayList<Post> posts = null;
			posts = pservice.loadAllPost();
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
			session.setAttribute("user", user);
			request.setAttribute("post", posts);
			rd.forward(request, response);
		} else {
			response.sendRedirect("/freeboard/writepost/writepost.jsp");
		}
	}
}