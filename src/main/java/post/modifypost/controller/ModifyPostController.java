package post.modifypost.controller;

import java.io.IOException;

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
 * Servlet implementation class ModifyPostController
 */
@WebServlet("/modifypost")
public class ModifyPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPostController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int modifypid = Integer.parseInt(request.getParameter("modifypid"));
		HttpSession session = request.getSession();
		
		Post post = new Post();
		post.setPid(modifypid);

		PostService service = new PostService();
		post = service.loadDetailPost(post);

		if(post != null) {					
			User user = (User)session.getAttribute("user");
			
			RequestDispatcher rd = request.getRequestDispatcher("/modifypost/modifypost.jsp");
			session.setAttribute("user", user);
			request.setAttribute("post", post);
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
