package ApiAjaxData01;
import		java.sql.*;
public class JDBCUtil {
	//	���� �� Ŭ������ new ��ų �� ����̹��� �ε��ϰ� �ʹ�.
	public JDBCUtil() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e) {
			System.out.println("����̹� �ε� ���� = " + e);
		}
	}
	
	//	�������� ������ ���Ѵ޶�� �ϸ� ������ ������ �Լ�
	public Connection getCON() {
		Connection	con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		}
		catch(Exception e) {
			System.out.println("���� ���� = " + e);
		}
		return con;
	}
	//	������ Statement�� ����� �޶�� �ϸ� ������� �Լ�
	public Statement getSTMT(Connection con) {
		Statement		stmt = null;
		try {
			stmt = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e) {
			System.out.println("Statement ���� ���� = " + e);
		}
		return stmt;
	}
	// �������� PreparedStatement�� ����� �޶�� �ϸ� 
	// ��� ������� �Լ�
	
	public PreparedStatement getPSTMT(Connection con, String sql){
		PreparedStatement pstmt = null;
				try{
					pstmt = con.prepareStatement(sql);
					
				}catch(Exception e){
					System.out.println("PreparedStatement ���� ����" + e);
				}
		
				return pstmt;
	}
	//	�������� �ڿ��� ���� �ʿ䰡 ������ ��� �ݾ��� �Լ�
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




