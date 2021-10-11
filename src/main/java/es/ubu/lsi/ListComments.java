package es.ubu.lsi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ubu.lsi.database.Comment;
import es.ubu.lsi.database.CommentsDAO;

/**
 * Servlet implementation class ListComments
 */
@WebServlet("/comments")
public class ListComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListComments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String page = "<h1> Comments list</h1>";
		try {
			CommentsDAO dao = new CommentsDAO();
			List<Comment> comments = dao.getComments();
			for (Comment c : comments) {
				page += String.format("<p> %s %s [%s] --> %s ", c.getName(), c.getSurname(), c.getDate(), c.getComments());
			}
			
			if (comments.size() == 0) {
				out.print("No comments in database");
			} else {
				out.print(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("Getting comments list error");
		}
		
		out.print("<br><br><br><a href=\"./formulario.html\">Back to form</a>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
