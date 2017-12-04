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


public class AreaSigunCode {
    
    private static String xml;
    

	
	public void sigunDataGet(String serviceKey, String areaCode, 
			String mobileOs, String mobileApp, String ourl, String id, String pw){
		
		try {
			StringBuilder urlBuilder = 
					new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"); /*URL*/
			urlBuilder.append("?"+"ServiceKey="+serviceKey);
			urlBuilder.append("&"+"numOfRows=999");
			urlBuilder.append("&"+"pageNo=1");
			urlBuilder.append("&"+"MobileOS="+mobileOs);
			urlBuilder.append("&"+"MobileApp="+mobileApp);
			urlBuilder.append("&"+"areaCode="+areaCode);
			
					
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
	
		System.out.println(xml);
		
		//	�뜝�럥�냱�뜝�럥堉�
	    try {
	    	System.out.println("ajax success");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docFactory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			Document doc = docbuilder.parse(is);
			Element element = doc.getDocumentElement();
			
			
			
			int length = element.getElementsByTagName("code").getLength();
			
			JDBCUtil db = new JDBCUtil();
			Connection con = db.getCON(ourl, id, pw);
			String sql = "insert into sigunTbl values ( ? , ?, ?)";
			PreparedStatement pstmt = db.getPSTMT(con, sql);
			
			for(int i=0; i<length;i++){
				  
				
				
                String siGunCode = element.getElementsByTagName("code").item(i).getFirstChild().getNodeValue(); //�뜝�럥裕욃뜝�럥諭쒐춯�씧�궚占쎈さ�슖�돦裕녶뜝�룞�삕占쎈꼶
                String siGunName = element.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
                pstmt.setString(1, areaCode);
				pstmt.setString(2, siGunCode);
				pstmt.setString(3, siGunName);
				pstmt.executeQuery();
				System.out.println(sql);
				
				
			}
			
			db.close(pstmt);
			db.close(con);
			System.out.println("Success	siGunguApiCode");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  System.out.println("end	");
       
	}

}
