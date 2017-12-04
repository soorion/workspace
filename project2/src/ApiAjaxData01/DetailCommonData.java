package ApiAjaxData01;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Util.JDBCUtil;




public class DetailCommonData {
  	
	public void detailDataGet(String serviceKey, String mobileOs, String mobileApp, 
			String contentId, String tableName, String ourl, String id, String pw) {
	 // public static void main(String args[]){	
    	String  xml;
    	JDBCUtil db = null;
    	Connection con = null;
    	PreparedStatement pstmt = null;
		try {

			StringBuilder urlBuilder = 
					new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon"); /*URL*/
			urlBuilder.append("?"+"serviceKey="+serviceKey);
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageSize=10");
			urlBuilder.append("&pageNo=1");
			urlBuilder.append("&startPage=1");
			urlBuilder.append("&MobileOS="+mobileOs);
			urlBuilder.append("&MobileApp="+mobileApp);
			urlBuilder.append("&contentId="+contentId);
			urlBuilder.append("&overviewYN=Y");
		
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
			
			org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
	        String xmlJSONObjString = xmlJSONObj.toString();
	        // xml -> json �쑝濡� 蹂��솚�븯硫� item�쑝濡� �븳踰덉뿉 臾띠씤�떎.
	        // System.out.println(xml);
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map = objectMapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});	       
	        Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
	        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
	        Map<String, Object> items = (Map<String, Object>) body.get("items");
	        Map<String, Object> itemList =  (Map<String, Object>) items.get("item");
	        
	        
	        // overview
	        System.out.println(itemList);
	        
	        String keyString[] = {"contentid", "contenttypeid", "overview"};
	     	
	        
	        
	        for(int i=0; i<keyString.length; i++){
	        	boolean keycheck = itemList.containsKey(keyString[i]);
	        		if(keycheck==false){
	        			itemList.put(keyString[i], "empty");
	        		}
	        	
	        }
	        
	        String val1 = itemList.get(keyString[0]).toString();
	        String val2 = itemList.get(keyString[1]).toString();
	        String val3 = itemList.get(keyString[2]).toString();
	        
	        
	        
	        db = new JDBCUtil();
	        con = db.getCON(ourl, id, pw);
	        String sql = "insert into " + tableName + " values ( ? , ?,  ? )";
	        pstmt = db.getPSTMT(con, sql);
	      
	        
	        pstmt.setString(1, val1);
	        pstmt.setString(2, val2);
	        pstmt.setString(3, val3);
	        pstmt.executeQuery();
	        
	        db.close(pstmt);
	        db.close(con);
	        
	        System.out.println(contentId +	"	" + tableName + "	success");
		}catch(Exception e){
		 System.out.println(e); 
		}
  }
}
	        