package com.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.VideoDAO;
import com.entity.Video;


/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public VideoDAO videoDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		this.videoDAO = new VideoDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageStr = request.getParameter("page");
		String limitStr = request.getParameter("limit");

		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int limit = limitStr == null ? 8 : Integer.parseInt(limitStr);

		int offset = limit * (page - 1);

		List<Video> listVideo =this.videoDAO.paginate(offset, limit);
		
		request.setAttribute("listVideo", listVideo);
		request.setAttribute("page", page);
		String view = "/view/admin/videos/Index.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/view/layout.jsp").forward(request, response);
	}

}
