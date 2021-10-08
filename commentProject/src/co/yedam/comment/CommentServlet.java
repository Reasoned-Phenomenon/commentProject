package co.yedam.comment;

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

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		
		String cmd = request.getParameter("cmd");
		CommentDAO dao = CommentDAO.getInstance();
		
		if ( cmd == null ) {
			
			out.println("<h1>빈 페이지입니다.</h1>");
			
		} else if (cmd.equals("list")) {
			
			//cmd값이 list이면 목록 보여줌
			System.out.println("<h1>리스트 페이지입니다.</h1>");
			List<Comment> list = dao.getCommentList();
			out.println(gson.toJson(list));
			
		} else if (cmd.equals("add")) {
			
			//cmd값이 add이면 추가기능 호출
			System.out.println("<h1>추가 페이지입니다.</h1>");
			
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			Comment comment = new Comment();
			comment.setName(name);
			comment.setContent(content);
			
			dao.insertComment(comment);
			
			System.out.println(comment);
			out.println(gson.toJson(comment));
		} else if (cmd.equals("mod")) {
			//cmd값이 mod이면 수정기능 호출
			out.println("<h1>수정 페이지입니다.</h1>");
		} else if (cmd.equals("del")) {
			//cmd값이 del이면 삭제기능 호출
			out.println("<h1>삭제 페이지입니다.</h1>");
		} else {
			out.println("<h1>" + cmd + "</h1>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
