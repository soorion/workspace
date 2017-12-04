package ApiAjaxData01;
import		java.sql.*;
public class JDBCUtil {
	//	나는 이 클래스를 new 시킬 때 드라이버를 로딩하고 싶다.
	public JDBCUtil() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e) {
			System.out.println("드라이버 로딩 에러 = " + e);
		}
	}
	
	//	누군가가 접속을 시켜달라고 하면 접속을 시켜줄 함수
	public Connection getCON() {
		Connection	con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		}
		catch(Exception e) {
			System.out.println("접속 에러 = " + e);
		}
		return con;
	}
	//	누군가 Statement를 만들어 달라고 하면 만들어줄 함수
	public Statement getSTMT(Connection con) {
		Statement		stmt = null;
		try {
			stmt = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e) {
			System.out.println("Statement 생성 에러 = " + e);
		}
		return stmt;
	}
	// 누군가가 PreparedStatement를 만들어 달라고 하면 
	// 대신 만들어줄 함수
	
	public PreparedStatement getPSTMT(Connection con, String sql){
		PreparedStatement pstmt = null;
				try{
					pstmt = con.prepareStatement(sql);
					
				}catch(Exception e){
					System.out.println("PreparedStatement 생성 에러" + e);
				}
		
				return pstmt;
	}
	//	누군가가 자원을 닫을 필요가 있으면 대신 닫아줄 함수
	public void close(Object target) {
		try {
			if(target instanceof ResultSet) {
				ResultSet 	temp = (ResultSet) target;
				temp.close();
			}
			else if(target instanceof Statement) {
				Statement	temp = (Statement) target;
				temp.close();
			}
			else if(target instanceof Connection) {
				Connection	temp = (Connection) target;
				temp.close();
			} else if(target instanceof PreparedStatement){
				PreparedStatement temp = (PreparedStatement) target;
				temp.close();
			}
		}
		catch(Exception e) {}
	}
	
}




