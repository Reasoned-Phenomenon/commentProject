package co.yedam.comment;

import co.yedam.common.DAO;

public class CommentDAO extends DAO {
	
	private static CommentDAO singleton = new CommentDAO();
	
	private CommentDAO() {
	}
	
	public static CommentDAO getInstance() {
		return singleton;
	}
	
	//글목록..
	public void getCommentList () {
		
	}
	//글 입력..
	public void insertComment () {
		
	}
	//글 수정..
	public void updateComment () {
		
	}
	
	public void deleteComment() {
		
	}
	
}
