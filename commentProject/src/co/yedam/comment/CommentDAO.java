package co.yedam.comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;

public class CommentDAO extends DAO {
	
	private static CommentDAO singleton = new CommentDAO();
	
	private CommentDAO() {
	}
	
	public static CommentDAO getInstance() {
		return singleton;
	}
	
	//글목록..
	public List<Comment> getCommentList () {
		
		connect();
		List<Comment> list = new ArrayList<>();
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM comments ORDER BY 1");
			
			while(rs.next()) {
				Comment cmt = new Comment();
				cmt.setId(rs.getString("id"));
				cmt.setContent(rs.getString("content"));
				cmt.setName(rs.getString("name"));
				
				list.add(cmt);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	//글 입력..
		//1)현재 id 체크 -> 2)id+1 값으로 글 등록 -> 3) 현재 id값 변경.
	public Comment insertComment (Comment comment) {
		
		connect();
		int currId = 0;
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT value FROM id_repository WHERE name='COMMENT'");
			
			if (rs.next()) {
				currId = rs.getInt("value");
				
			}
			
			currId++;
			
			psmt = conn.prepareStatement("INSERT INTO comments VALUES(?,?,?)");
			psmt.setInt(1, currId);
			psmt.setString(2, comment.getName());
			psmt.setString(3, comment.getContent());
			int r = psmt.executeUpdate();
			System.out.println(r+"건 입력.");
			
			psmt = conn.prepareStatement("UPDATE id_repository SET value=? WHERE name='COMMENT'");
			psmt.setInt(1, currId);
			r = psmt.executeUpdate();
			System.out.println(r+"건 변경.");
			
			conn.commit();
			comment.setId(String.valueOf(currId));
			return comment;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		} finally {
			disconnect();
		}
		
	}
	
	//글 수정..
	public Comment updateComment (Comment comment) {
		
		connect();
		String sql = "UPDATE comments SET name=?, content=? WHERE id=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, comment.getName());
			psmt.setString(2, comment.getContent());
			psmt.setString(3, comment.getId());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경.");
			
			return comment;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		
		
	}
	
	public String deleteComment(String id) {
		
		connect();
		
		try {
			psmt = conn.prepareStatement("DELETE FROM comments WHERE id=?");
			psmt.setString(1, id);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제.");
			
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		
	}
	
}
