package es.ubu.lsi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ubu.lsi.database.Comment;
import es.ubu.lsi.database.CommentsDAO;

/**
 * Servlet implementation class AddComent
 */
@WebServlet("/new")
public class AddComent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new UnavailableException("No disponible");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// getting parameters
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String comments = request.getParameter("comments");

			// creating new comment and insert into database
			Comment c = new Comment(name, surname, comments);
			CommentsDAO dao = new CommentsDAO();
			dao.add(c);

			// preparing redirection
			RequestDispatcher rd = request.getRequestDispatcher("comments");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("Insert data error");
		}
	}

}
