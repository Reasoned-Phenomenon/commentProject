package co.yedam.fileboard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;

public class FileDAO extends DAO{
	
	
	public List<FileVO> getFileList() {
		connect();
		List<FileVO> list = new ArrayList<>();
		String sql = "SELECT * FROM fileboard ORDER BY 1";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				
				FileVO vo = new FileVO();
				vo.setAuthor(rs.getString("author"));
				vo.setDay(rs.getString("day"));
				vo.setFileName(rs.getString("filename"));
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
	

	//파일 업로그 처리.
	//서블릿에서 파일 업로드, db저장.
	public FileVO uploadFile(String author, String title, String file) {
		
		connect();
		
		String sql = "INSERT INTO fileboard VALUES(?,?,?,?,SYSDATE)";
		
		try {
			int nextNum = -1;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT NVL(MAX(num),0)+1 FROM fileboard");
			if ( rs.next()) {
				nextNum = rs.getInt(1);
			}
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, nextNum);
			psmt.setString(2, author);
			psmt.setString(3, title);
			psmt.setString(4, file);
			
			int r = psmt.executeUpdate();
			System.out.println(r+"건 입력.");
			
			FileVO vo = new FileVO();
			vo.setNum(nextNum);
			vo.setAuthor(author);
			vo.setDay(null);
			vo.setFileName(file);
			vo.setTitle(title);
			
			return vo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		
	}
	

}
