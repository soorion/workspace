package ApiAjaxData01;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Util.JDBCUtil;


public class AreaCode {
    
    private static String xml;
    

	
	public void areaDataGet(String serviceKey, String mobileOs, String mobileApp,
			String ourl, String id, String pw){
		
		try {
			StringBuilder urlBuilder = 
					new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"); /*URL*/
			urlBuilder.append("?"+"ServiceKey="+serviceKey);
			urlBuilder.append("&"+"numOfRows=100");
			urlBuilder.append("&"+"pageNo=1");
			urlBuilder.append("&"+"MobileOS="+mobileOs);
			urlBuilder.append("&"+"MobileApp="+mobileApp);
			
			
			/*urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8")+ "=6EJNTamkI39r7f8Igxs0usZiNoa0qpP16oujfSU2XVfPKMWWJEuoYeviOKJa4fSTcsriiJi52gvECu4BgoWPNQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("SERVICE_KEY", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("9999", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("startPage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));*/
					
			URL url = new URL(urlBuilder.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			//	ajax = xml	/ json = application/json
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while (true) {
			    String line = br.readLine();
			    if (line == null)
			        break;
			    sb.append(line);
			}
			xml = sb.toString();
			br.close();
			conn.disconnect();
			
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
	
		//System.out.println(xml);
		
		//	�뙆�떛
	    try {
	    	System.out.println("ajax 데이터 가져오기 성공");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docFactory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			Document doc = docbuilder.parse(is);
			Element element = doc.getDocumentElement();
			
			
			
			int length = element.getElementsByTagName("code").getLength();
			
			JDBCUtil db = new JDBCUtil();
			Connection con = db.getCON(ourl, id, pw);
			String sql = "insert into areaTbl values ( ? , ?)";
			PreparedStatement pstmt = db.getPSTMT(con, sql);
			
			for(int i=0; i<length;i++){
				 // �깭洹� �궡�쓽 泥ル쾲吏� 媛믪쓣 value�뿉 ���옣
				
                String areaCode = element.getElementsByTagName("code").item(i).getFirstChild().getNodeValue(); //�뒪�듃留곸쑝濡쒕��솚
                String areaName = element.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
               
				pstmt.setString(1, areaCode);
				pstmt.setString(2, areaName);
				pstmt.executeQuery();
				System.out.println(sql);
				
				
			}
			
			db.close(pstmt);
			db.close(con);
			System.out.println("Success	AreaApiCode");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
       
	}

}
