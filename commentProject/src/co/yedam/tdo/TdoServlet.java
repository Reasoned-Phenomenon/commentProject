package co.yedam.tdo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/tdoServlet")
public class TdoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TdoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		
		String cmd = request.getParameter("cmd");
		TdoDAO dao = new TdoDAO();
		
		if ( cmd == null ) {
			
			out.println("<h1>빈 페이지입니다.</h1>");
			
		} else if (cmd.equals("list")) {
			
			List<Tdo> list = dao.showList();
			out.println(gson.toJson(list));
			
		} 
		
		else if (cmd.equals("add")) {
			
			String content = request.getParameter("content");
			Tdo tdo = new Tdo();
			tdo.setContent(content);
			
			dao.addList(tdo);
			
			out.println(gson.toJson(tdo));
			
		}  
		
		
		else if (cmd.equals("del")) {
			
			String id = request.getParameter("id");
			
			if(dao.deleteList(id) == null) {
				
				//{"retCode":"fail"}
				out.println("{\"retCode\":\"fail\"}");
				return;
				
			} else {
				
				out.println("{\"retCode\":\"success\"}");
				
			}
			
		} 
	
	
	
	}

}
