package co.yedam.tdo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;

public class TdoDAO extends DAO{

	public List<Tdo> showList () {
		
		connect();
		List<Tdo> list = new ArrayList<>();
		
		String sql = "SELECT id, content FROM tdl ORDER BY 1";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
			Tdo tdo = new Tdo();
			tdo.setId(rs.getString("id"));
			tdo.setContent(rs.getString("content"));
			
			list.add(tdo);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public Tdo addList (Tdo tdo) {
		
		connect();
		
		try {
			
			String sql = "INSERT INTO tdl(id,content) VALUES(tdl_seq.nextval, ? ) ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, tdo.getContent());
			int r = psmt.executeUpdate();
			System.out.println(r+"건 입력");
			//id값 가져오는 게 문제
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tdl ORDER BY 1");
			
			while ( rs.next()) {
				tdo.setId(rs.getString("id"));
			}
			
			return tdo;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return tdo;
		
	}
	
	public String deleteList (String id) {
		
		connect();
		
		String sql = "DELETE FROM tdl WHERE id=?";
		
		try {
			psmt = conn.prepareStatement(sql);
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
