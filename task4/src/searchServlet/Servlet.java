package searchServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searchResult.SearchDriver;

/**
 * Servlet implementation class Servelt
 */
@WebServlet("/Servelt")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String result = "";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = new String(request.getParameter("search").getBytes("ISO-8859-1"),"utf-8");
		try {
			result = searchResult.SearchDriver.Search(keyword);
			response.getWriter().write(keyword);
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("keyword", keyword);
			request.setAttribute("result", result);
			request.getRequestDispatcher("show.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("keyword", keyword);
			request.setAttribute("error", result);
			request.getRequestDispatcher("show.jsp").forward(request, response);
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
