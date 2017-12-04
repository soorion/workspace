package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ListSizeUtil {

	public static ArrayList getContentid(String tableName, String areaCode, String contenttypeid,
			String url, String id, String pw){
		JDBCUtil db = new JDBCUtil();
		Connection conn = null;
		Statement st = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> contentidList = new ArrayList<String>();
		
		try {
			conn = db.getCON(url, id, pw);
			st = db.getSTMT(conn);
			String sql = "select contentid from " + tableName + " where areaCode = " + areaCode +
					"and contenttypeid= " + contenttypeid;
			
			rs = st.executeQuery(sql);
			while(rs.next()){
				String data = rs.getString("contentid");
				contentidList.add(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(rs);
			db.close(pstmt);
			db.close(conn);
		}
		
		System.out.println("  areaCode = " + areaCode + "	" 	 + tableName + " 'size = " + contentidList.size());
		return contentidList;
	}
}
