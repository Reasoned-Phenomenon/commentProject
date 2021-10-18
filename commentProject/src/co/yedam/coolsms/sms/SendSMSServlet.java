package co.yedam.coolsms.sms;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import co.yedam.coolsms.Coolsms;

@WebServlet("/SendSMSServlet")
public class SendSMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendSMSServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		
		String receipt = request.getParameter("receipt");
		String content = request.getParameter("content");
		System.out.println(receipt);
		System.out.println(content);
		String api_key = "NCSEQHJODPXAGJD7";
		String api_secret = "H8A0IJOKT99PKI9KX6FSOAWPKHRFPKFQ";
		Coolsms coolsms = new Coolsms(api_key, api_secret);
	
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", receipt); // 수신번호

		set.put("from", "01097759549"); // 발신번호
		set.put("text", content); // 문자내용
		set.put("type", "sms"); // 문자 타입

		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		if ((Boolean) result.get("status") == true) {
			System.out.println("성공");			
			System.out.println(result.get("group_id")); // 그룹아이디
			System.out.println(result.get("result_code")); // 결과코드
			System.out.println(result.get("result_message"));  // 결과메시지
			System.out.println(result.get("success_count")); // 성공갯수
			System.out.println(result.get("error_count"));  // 발송실패 메시지 수
			
			response.getWriter().println("성공");
		} else {
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
			
			response.getWriter().println("실패");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
