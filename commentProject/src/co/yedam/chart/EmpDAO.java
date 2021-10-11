package co.yedam.chart;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import co.yedam.common.DAO;

public class EmpDAO extends DAO{
	
	//교수님
	//데이터가 있거나 , 에러 =>false
	//없는게 확실해 => true
	public Map<String, Integer> getDeptByEmp() {
		
		connect();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String sql = "SELECT d.department_name, count(1)\n"
				+ "FROM employees e\n"
				+ "JOIN departments d\n"
				+ "ON e.department_id = d.department_id\n"
				+ "GROUP BY d.department_name";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while ( rs.next() ) {
				map.put(rs.getString(1), rs.getInt(2));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return map;
	}
	
}
