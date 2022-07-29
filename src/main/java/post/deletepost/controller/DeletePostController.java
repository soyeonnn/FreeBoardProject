package post.deletepost.controller;

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
 * Servlet implementation class DeletePostController
 */
@WebServlet("/deletepost")
public class DeletePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePostController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int delpid = Integer.parseInt(request.getParameter("delpid"));
		
		PostService service = new PostService();
		int result = service.delPost(delpid);

		if(result == 1) {
			ArrayList<Post> posts = null;
			posts = service.loadAllPost();
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
			
			session.setAttribute("user", user);
			request.setAttribute("post", posts);
			rd.forward(request, response);
		} else {
			response.sendRedirect("/freeboard/viewpost/viewpost.jsp");
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
