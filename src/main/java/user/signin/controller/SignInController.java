package user.signin.controller;

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
import user.service.UserService;
import user.vo.User;

/**
 * Servlet implementation class SignInController
 */
@WebServlet("/signin")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInController() {
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
		HttpSession session = request.getSession();
		
		String id = request.getParameter("userID");
		String pw = request.getParameter("userPassword");
		
		User user = new User();
		user.setUid(id);
		user.setPw(pw);
		
		UserService service = new UserService();
		user = service.signInUser(user);

		if(user != null) {
			PostService pservice = new PostService();
			ArrayList<Post> posts = null;
			posts = pservice.loadAllPost();
	
			RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
			session.setAttribute("user", user);
			request.setAttribute("post", posts);
			rd.forward(request, response);
		} else {
			response.sendRedirect("/freeboard/signin/signin.html");
		}
	}

}
